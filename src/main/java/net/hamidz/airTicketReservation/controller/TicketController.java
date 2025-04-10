package net.hamidz.airTicketReservation.controller;

import net.hamidz.airTicketReservation.entity.Ticket;
import net.hamidz.airTicketReservation.entity.User;
import net.hamidz.airTicketReservation.service.TicketService;
import net.hamidz.airTicketReservation.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/api/v1/tickets")
public class TicketController {

    TicketService ticketService;
    UserService userService;
    @Autowired
    public TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @PostMapping("/register-ticket/{username}")
    public ResponseEntity<Ticket> registerTicket(@RequestBody Ticket ticket, @PathVariable String username) {
        try {

            ticketService.saveTicket(ticket, username);
            return new ResponseEntity<>(ticket, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-tickets/{username}")
    public ResponseEntity<List<Ticket>> getAll(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        List<Ticket> allTickets = user.getTickets();
        if(allTickets != null && !allTickets.isEmpty()) {
            return new ResponseEntity<>(allTickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Optional<Ticket>> getTicketById(@PathVariable ObjectId ticketId) {
        return new ResponseEntity<>(ticketService.findTicketById(ticketId), HttpStatus.OK);
    }

    @DeleteMapping("/{username}/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable ObjectId ticketId, @PathVariable String username) {
        ticketService.deleteTicketById(ticketId, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{username}/{ticketId}")
    public ResponseEntity<Ticket> updateTicket( @PathVariable ObjectId ticketId,
                                                @RequestBody Ticket newTicket,
                                                @PathVariable String username ) {
        Ticket ticket = ticketService.findTicketById(ticketId).orElse(null);
        if(ticket != null) {
            ticket.setTicketType(ticket.getTicketType() != null && !ticket.getTicketType().isEmpty() ?
                    newTicket.getTicketType() : ticket.getTicketType());
            ticket.setTicketNumber(ticket.getTicketNumber() != null && !ticket.getTicketNumber().equals(null)? newTicket.getTicketNumber() : ticket.getTicketNumber());
        }
        return new ResponseEntity<>(ticketService.saveTicket(ticket), HttpStatus.OK);
    }
}
