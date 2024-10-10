package Homework9.repository;

import Homework9.model.Ticket.TicketType;
import Homework9.model.Ticket;
import Homework9.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepository {

    private final SessionFactory sessionFactory;

    public void createUser(User user){
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public User getUser(int userId){
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            return session.get(User.class, userId);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteUser(int userId){
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            User user = session.get(User.class, userId);
            session.remove(user);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateUserAndTickets(int userId, TicketType newTicketType){
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            User user = session.get(User.class, userId);
            for(Ticket ticket : user.getTickets()){
                ticket.setTicketType(newTicketType);
            }
            session.merge(user);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
