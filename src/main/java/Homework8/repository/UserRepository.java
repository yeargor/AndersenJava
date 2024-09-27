package Homework8.repository;

import Homework8.config.JDBCConfig;
import Homework8.model.Ticket;
import Homework8.model.Ticket.TicketType;
import Homework8.model.User;
import org.hibernate.Session;

public class UserRepository {

    public void createUser(User user){
        try(Session session = JDBCConfig.getSession()){

            session.beginTransaction();

            session.persist(user);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public User getUser(int userId){
        try(Session session = JDBCConfig.getSession()){
            session.beginTransaction();
            User user = session.get(User.class, userId);
            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteUser(int userId){
        try(Session session = JDBCConfig.getSession()){
            session.beginTransaction();
            User user = session.get(User.class, userId);
            session.remove(user);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateUserAndTickets(int userId, TicketType newTicketType){
        try(Session session = JDBCConfig.getSession()){
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
