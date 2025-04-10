package net.hamidz.airTicketReservation.controller;

import net.hamidz.airTicketReservation.entity.Flight;
import net.hamidz.airTicketReservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        return new ResponseEntity<>(flightService.createFlight(flight), HttpStatus.CREATED);
    }

    // ✅ 2. Get All Flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

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
