package Homework9.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name="tickets")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "userId")
    @ToString.Exclude
    User user;
    @Enumerated(EnumType.STRING)
    TicketType ticketType;
    Date creationDate;

    public Ticket(User user, TicketType ticketType) {
        this.user = user;
        this.ticketType = ticketType;
        this.creationDate = new Date(System.currentTimeMillis());
    }

    public enum TicketType {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}
