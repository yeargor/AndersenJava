package Homework8.model;

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
    private User user;
    @Enumerated(EnumType.STRING)
    TicketType ticketType;
    Date creationDate;

    public Ticket(User user, TicketType ticketType, Date creationDate) {
        this.user = user;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public enum TicketType {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}
