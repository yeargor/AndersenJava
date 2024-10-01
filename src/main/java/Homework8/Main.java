package Homework8;

import Homework8.model.Ticket;
import Homework8.model.Ticket.TicketType;
import Homework8.model.User;
import Homework8.repository.TicketRepository;
import Homework8.repository.UserRepository;
import java.sql.Date;

public class Main {

    public static final UserRepository USER_REPOSITORY = new UserRepository();
    public static final TicketRepository TICKET_REPOSITORY = new TicketRepository();

    public static void main(String[] args) {

        User user  = new User("Yahor4");
        USER_REPOSITORY.createUser(user);

        Ticket ticket = new Ticket(user, TicketType.DAY);
        TICKET_REPOSITORY.createTicket(ticket);

        System.out.println(TICKET_REPOSITORY.getTicketById(42));
        System.out.println(TICKET_REPOSITORY.getTicketsByUserId(5));
        System.out.println(USER_REPOSITORY.getUser(5));

        TICKET_REPOSITORY.updateTicketType(42, TicketType.YEAR);

        USER_REPOSITORY.deleteUser(7);

        USER_REPOSITORY.updateUserAndTickets(5, TicketType.DAY);
    }
}