package net.hamidz.airTicketReservation.service;

import net.hamidz.airTicketReservation.entity.Ticket;
import net.hamidz.airTicketReservation.entity.User;
import net.hamidz.airTicketReservation.repository.TicketRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    TicketRepository ticketRepository;
    UserRepository userRepository;
    UserService userService;
    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void saveTicket(Ticket ticket, String username) {
        User user = userRepository.findByUsername(username);
        ticket.setBookingDate(LocalDateTime.now());
        Ticket savedTicket = ticketRepository.save(ticket);
        user.getTickets().add(savedTicket);
        userService.saveUser(user);
    }

    public Ticket saveTicket(Ticket ticket) {
        ticket.setBookingDate(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findTicketById(ObjectId id) {
        return ticketRepository.findById(id);
    }

    public void deleteTicketById(ObjectId id, String username) {
        User user = userRepository.findByUsername(username);
        user.getTickets().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        ticketRepository.deleteById(id);
    }
}
