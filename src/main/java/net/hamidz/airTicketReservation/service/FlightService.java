package net.hamidz.airTicketReservation.service;

import net.hamidz.airTicketReservation.entity.Flight;
import net.hamidz.airTicketReservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Set<Integer> getAvailableSeats(String flightNumber) {
        Flight flight = getFlightByNumber(flightNumber);
        return flight.getAvailableSeats();
    }

    public Set<Integer> getBookedSeats(String flightNumber) {
        Flight flight = getFlightByNumber(flightNumber);
        return flight.getBookedSeats();
    }

    public String bookSeat(String flightNumber, int seatNumber) {
        Flight flight = getFlightByNumber(flightNumber);

        if (seatNumber < 1 || seatNumber > 30) {
            return "Invalid seat number!";
        }

        if (flight.getBookedSeats().contains(seatNumber)) {
            return "Seat already booked!";
        }

        flight.getBookedSeats().add(seatNumber);
        flightRepository.save(flight);
        return "Seat " + seatNumber + " booked successfully.";
    }

    private Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }
}

