package Homework5;

import com.google.gson.annotations.JsonAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tools.adapters.LocalDateAdapter;
import tools.validators.BusTicketValidator;
import tools.warnings.DateWarning;
import tools.warnings.EvenWarning;
import tools.warnings.NullableWarning;
import tools.warnings.TypeWarning;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BusTicket {

    @NullableWarning(message = "ticketClass cannot be null")
    private String ticketClass;
    @TypeWarning
    @NullableWarning(message = "ticketType cannot be null")
    private String ticketType;
    @JsonAdapter(LocalDateAdapter.class)
    @NullableWarning(message = "startDate cannot be null")
    @DateWarning(message = "startDate cannot be in the future")
    private LocalDate startDate;
    @NullableWarning(message = "price cannot be null")
    @EvenWarning(message = "price must be even")
    private int price;

    public static BusTicket createBusTicket(String ticketClass, String ticketType, LocalDate startDate, int price) {

        BusTicket busTicket = new BusTicket(ticketClass, ticketType, startDate, price);
        BusTicketValidator busTicketValidator = new BusTicketValidator();
        List<String> validationMessages = busTicketValidator.validate(busTicket);

        if (!validationMessages.isEmpty()) {
            for (String message : validationMessages) {
                System.err.println(message);
            }
            return null;
        }

        return busTicket;

    }
}
