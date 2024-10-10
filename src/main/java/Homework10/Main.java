package Homework10;

import Homework10.repository.TicketJsonMapper;
import Homework10.model.Ticket;
import Homework10.model.User;
import Homework10.repository.TicketRepository;
import Homework10.repository.UserRepository;
import Homework10.config.AppContextConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final AnnotationConfigApplicationContext CONTEXT =
            new AnnotationConfigApplicationContext(AppContextConfig.class);
    private static final UserRepository USER_REPOSITORY = CONTEXT.getBean(UserRepository.class);
    private static final TicketRepository TICKET_REPOSITORY = CONTEXT.getBean(TicketRepository.class);

    public static void main(String[] args) throws IOException {

        User user = USER_REPOSITORY.getUser(8);
        System.out.println("User before update: " + user);
        TICKET_REPOSITORY.createTicket(new Ticket(user, Ticket.TicketType.DAY));
        System.out.println("User after update: " + USER_REPOSITORY.getUser(8));

        TicketJsonMapper ticketJsonMapper = CONTEXT.getBean(TicketJsonMapper.class);
        List<Ticket> ticketList = ticketJsonMapper.loadTicketsFromFile();
        System.out.println(ticketList);
    }
}