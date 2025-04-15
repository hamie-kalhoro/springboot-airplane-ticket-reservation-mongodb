package net.hamidz.airTicketReservation.repository;

import net.hamidz.airTicketReservation.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByFlightId(String flightId);

    List<Booking> findByUserId(String userId);
}

