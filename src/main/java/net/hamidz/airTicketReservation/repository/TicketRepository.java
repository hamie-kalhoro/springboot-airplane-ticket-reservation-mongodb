package net.hamidz.airTicketReservation.repository;

import net.hamidz.airTicketReservation.entity.Ticket;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, ObjectId> {
}
