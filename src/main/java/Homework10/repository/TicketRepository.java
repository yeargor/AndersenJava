package Homework10.repository;

import Homework10.model.Ticket;
import Homework10.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class TicketRepository {
    private final SessionFactory SESSION_FACTORY;

    @Transactional
    public void createTicket(Ticket ticket){
        Session session = SESSION_FACTORY.getCurrentSession();
        User user = ticket.getUser();
        user.setActive(true);
        session.merge(user);
        session.persist(ticket);
    }


}
