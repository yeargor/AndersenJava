package Homework9.repository;

import Homework9.model.Ticket.TicketType;
import Homework9.model.Ticket;
import Homework9.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@AllArgsConstructor
public class TicketRepository {
    private final SessionFactory SESSION_FACTORY;

    public void createTicket(Ticket ticket){
        try(Session session = SESSION_FACTORY.getCurrentSession()){
            session.beginTransaction();
            session.persist(ticket);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Ticket getTicketById(int id){
        try(Session session = SESSION_FACTORY.getCurrentSession()){
            session.beginTransaction();
            return session.get(Ticket.class, id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Set<Ticket> getTicketsByUserId(int userId){
        try(Session session = SESSION_FACTORY.getCurrentSession()){
            session.beginTransaction();
            User user = session.get(User.class, userId);
            return user.getTickets();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateTicketType(int id, TicketType newTicketType){
        try(Session session = SESSION_FACTORY.getCurrentSession()){
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            ticket.setTicketType(newTicketType);
            session.merge(ticket);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
