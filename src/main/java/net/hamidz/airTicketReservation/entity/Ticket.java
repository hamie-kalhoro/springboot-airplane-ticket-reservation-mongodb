package net.hamidz.airTicketReservation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "tickets")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Ticket {
    @Id
    private ObjectId id;
    @NonNull
    @JsonProperty("ticketType")
    private String ticketType;
    @NonNull
    @JsonProperty("ticketNumber")
    private Long ticketNumber;
    @NonNull
    @JsonProperty("bookingDate")
    private LocalDateTime bookingDate;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public @NonNull String getTicketType() {
        return ticketType;
    }

    public void setTicketType(@NonNull String ticketType) {
        this.ticketType = ticketType;
    }

    public @NonNull Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(@NonNull Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public @NonNull LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(@NonNull LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
