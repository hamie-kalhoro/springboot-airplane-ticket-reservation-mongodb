package net.hamidz.airTicketReservation.repository;

import net.hamidz.airTicketReservation.entity.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface FlightRepository extends MongoRepository<Flight, String> {
    Optional<Flight> findByFlightNumber(String flightNumber);
}

