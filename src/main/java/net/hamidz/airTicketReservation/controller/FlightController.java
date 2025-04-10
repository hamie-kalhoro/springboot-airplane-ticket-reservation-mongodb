package net.hamidz.airTicketReservation.controller;

import net.hamidz.airTicketReservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/{flightNumber}/available-seats")
    public ResponseEntity<Set<Integer>> getAvailableSeats(@PathVariable String flightNumber) {
        return ResponseEntity.ok(flightService.getAvailableSeats(flightNumber));
    }

    @GetMapping("/{flightNumber}/booked-seats")
    public ResponseEntity<Set<Integer>> getBookedSeats(@PathVariable String flightNumber) {
        return ResponseEntity.ok(flightService.getBookedSeats(flightNumber));
    }

    @PostMapping("/{flightNumber}/book")
    public ResponseEntity<String> bookSeat(@PathVariable String flightNumber, @RequestParam int seatNumber) {
        return ResponseEntity.ok(flightService.bookSeat(flightNumber, seatNumber));
    }
}
