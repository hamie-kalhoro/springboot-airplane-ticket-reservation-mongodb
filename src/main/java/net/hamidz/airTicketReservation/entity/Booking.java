package net.hamidz.airTicketReservation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Flight flight;

    private int seatNumber;
    private LocalDateTime bookingTime;

    private SeatClass seatClass; // NEW

}


