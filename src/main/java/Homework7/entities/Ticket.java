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
    int user_id;
    TicketType ticketType;
    Date creation_date;

    //id field is generated
    public Ticket(int user_id, TicketType ticketType, Date creation_date) {
        this.user_id = user_id;
        this.ticketType = ticketType;
        this.creation_date = creation_date;
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
