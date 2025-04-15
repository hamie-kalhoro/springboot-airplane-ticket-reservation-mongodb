package net.hamidz.airTicketReservation.dto;

import lombok.Getter;
import lombok.Setter;
import net.hamidz.airTicketReservation.entity.SeatClass;

@Getter
@Setter
public class BookingRequest {

    private String userId;
    private String flightId;
    private SeatClass seatClass; // NEW

}

