package net.hamidz.airTicketReservation.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "flights")
public class Flight {

    @Id
    private String id;
    private String flightNumber;
    private Set<Integer> bookedSeats = new HashSet<>();

    public Flight() {
        // default constructor
    }

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Set<Integer> getAvailableSeats() {
        Set<Integer> availableSeats = new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            if (!bookedSeats.contains(i)) {
                availableSeats.add(i);
            }
        }
        return availableSeats;
    }

    // Getters and Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public Set<Integer> getBookedSeats() { return bookedSeats; }
    public void setBookedSeats(Set<Integer> bookedSeats) { this.bookedSeats = bookedSeats; }
}

