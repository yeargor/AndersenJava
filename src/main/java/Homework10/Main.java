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

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppContextConfig.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        TicketRepository ticketRepository = context.getBean(TicketRepository.class);

        User user = userRepository.getUser(8);
        System.out.println("User before update: " + user);
        ticketRepository.createTicket(new Ticket(user, Ticket.TicketType.DAY));
        System.out.println("User after update: " + userRepository.getUser(8));

        TicketJsonMapper ticketJsonMapper = context.getBean(TicketJsonMapper.class);
        List<Ticket> ticketList = ticketJsonMapper.loadTicketsFromFile();
        System.out.println(ticketList);
    }
}