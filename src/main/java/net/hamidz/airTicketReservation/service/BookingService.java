package net.hamidz.airTicketReservation.service;

import net.hamidz.airTicketReservation.dto.BookingRequest;
import net.hamidz.airTicketReservation.entity.Booking;
import net.hamidz.airTicketReservation.entity.Flight;
import net.hamidz.airTicketReservation.entity.SeatClass;
import net.hamidz.airTicketReservation.entity.User;
import net.hamidz.airTicketReservation.repository.BookingRepository;
import net.hamidz.airTicketReservation.repository.FlightRepository;
import net.hamidz.airTicketReservation.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private UserRepository userRepo;

    public Booking bookSeat(BookingRequest request) {
        Flight flight = flightRepo.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        User user = userRepo.findById(new ObjectId(request.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Count booked seats for this flight and seat class
        long bookedCount = bookingRepo.findByFlightId(flight.getId()).stream()
                .filter(b -> b.getSeatClass() == request.getSeatClass())
                .count();

        // Get total seats in the selected class
        int totalSeats = switch (request.getSeatClass()) {
            case ECONOMY -> flight.getEconomySeats();
            case BUSINESS -> flight.getBusinessSeats();
            case FIRST -> flight.getFirstClassSeats();
        };

        if (bookedCount >= totalSeats) {
            throw new RuntimeException("No available seats in " + request.getSeatClass() + " class.");
        }

        // Create booking
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setUser(user);
        booking.setSeatClass(request.getSeatClass());
        booking.setSeatNumber((int) bookedCount + 1);
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepo.save(booking);
    }

    public int getAvailableSeatsByClass(String flightId, SeatClass seatClass) {
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        long bookedCount = bookingRepo.findByFlightId(flightId).stream()
                .filter(b -> b.getSeatClass() == seatClass)
                .count();

        int totalSeats = switch (seatClass) {
            case ECONOMY -> flight.getEconomySeats();
            case BUSINESS -> flight.getBusinessSeats();
            case FIRST -> flight.getFirstClassSeats();
        };

        return totalSeats - (int) bookedCount;
    }

    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepo.findByUserId(userId);
    }

    public List<Booking> getBookingsByFlightAndClass(String flightId, SeatClass seatClass) {
        return bookingRepo.findByFlightId(flightId).stream()
                .filter(b -> b.getSeatClass() == seatClass)
                .toList();
    }

    public List<Booking> getAllBookingsForFlight(String flightId) {
        return bookingRepo.findByFlightId(flightId);
    }
}

