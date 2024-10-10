package Homework9;

import Homework9.config.AppContextConfig;
import Homework9.model.Ticket.TicketType;
import Homework9.repository.TicketRepository;
import Homework9.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static final AnnotationConfigApplicationContext CONTEXT =
            new AnnotationConfigApplicationContext(AppContextConfig.class);
    public static final UserRepository USER_REPOSITORY = CONTEXT.getBean(UserRepository.class);
    public static final TicketRepository TICKET_REPOSITORY = CONTEXT.getBean(TicketRepository.class);

    public static void main(String[] args) {
        System.out.println(TICKET_REPOSITORY.getTicketById(42));
        System.out.println(TICKET_REPOSITORY.getTicketsByUserId(5));
        System.out.println(USER_REPOSITORY.getUser(5));

        TICKET_REPOSITORY.updateTicketType(42, TicketType.YEAR);
        USER_REPOSITORY.updateUserAndTickets(5, TicketType.DAY);
    }
}