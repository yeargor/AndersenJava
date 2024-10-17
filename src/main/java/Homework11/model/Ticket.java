package Homework11.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="tickets")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "userid")
    @ToString.Exclude
    @JsonBackReference
    User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "tickettype")
    TicketType ticketType;
    @CreatedDate
    @Column(name = "creationdate")
    LocalDate creationDate;

    public Ticket(User user, TicketType ticketType) {
        this.user = user;
        this.ticketType = ticketType;
    }

    public enum TicketType {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}
