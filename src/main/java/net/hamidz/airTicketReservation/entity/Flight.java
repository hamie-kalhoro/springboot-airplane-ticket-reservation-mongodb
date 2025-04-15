package net.hamidz.airTicketReservation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "flights")
public class Flight {

    @Id
    private String id;
    private String flightNumber;
    private String departure;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private int economySeats;
    private int businessSeats;
    private int firstClassSeats;

    // Optional: Add these if you want to track seat numbers per class
    private Set<Integer> bookedEconomySeats = new HashSet<>();
    private Set<Integer> bookedBusinessSeats = new HashSet<>();
    private Set<Integer> bookedFirstClassSeats = new HashSet<>();

}
