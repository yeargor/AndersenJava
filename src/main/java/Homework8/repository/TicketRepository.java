package Homework8.repository;

import Homework8.config.JDBCConfig;
import Homework8.model.Ticket;
import Homework8.model.User;
import org.hibernate.Session;
import Homework8.model.Ticket.TicketType;
import java.util.Set;

public class TicketRepository {

    public void createTicket(Ticket ticket){
        try(Session session = JDBCConfig.getSession()){

            session.beginTransaction();

            session.persist(ticket);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Ticket getTicketById(int id){
        try(Session session = JDBCConfig.getSession()){
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            return ticket;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Set<Ticket> getTicketsByUserId(int userId){
        try(Session session = JDBCConfig.getSession()){
            session.beginTransaction();

            User user = session.get(User.class, userId);
            return user.getTickets();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void updateTicketType(int id, TicketType newTicketType){
        try(Session session = JDBCConfig.getSession()){
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
