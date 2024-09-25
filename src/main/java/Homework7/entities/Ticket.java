package Homework7.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Ticket {

    int id;
    int userId;
    TicketType ticketType;
    Date creationDate;

    public Ticket(int userId, TicketType ticketType, Date creationDate) {
        this.userId = userId;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public String getTicketTypeName() {
        return ticketType.name();
    }

    public enum TicketType {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}
