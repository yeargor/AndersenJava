package Homework7;

import Homework7.dao.DaoFactory;
import Homework7.dao.implementation.TicketDao;
import Homework7.dao.implementation.UserDao;
import Homework7.entities.Ticket;
import java.sql.Date;
import java.util.List;
import java.util.Random;

public class Program {

    public static void main(String[] args) {

        TicketDao ticketDao = DaoFactory.createTicketDao();
        UserDao userDao = DaoFactory.createUserDao();

//        int amount = 5;
//        Ticket[] ticketsArray = generateUserTickets(amount,5);
//
//        for (Ticket i : ticketsArray){
//            ticketDao.insert(i);
//        }

        System.out.println("ticket with id = 8: " + ticketDao.findById(8));
        System.out.println("user with id = 1: " + userDao.findById(1));

        ticketDao.updateTicketType(8, "MONTH");
        ticketDao.updateTicketType(8, "DAY");

        System.out.println("Checking ticket type after changes: " + ticketDao.findById(8).getTicketType());

        List<Ticket> tickets = ticketDao.findByUserId(5);
        for(Ticket ticket : tickets){
            System.out.println(ticket.toString());
        }

        //ticketDao.deleteById(29);
    }

    private static Ticket[] generateUserTickets(int amount, int user_id){

        Random random = new Random();
        Ticket[] tickets = new Ticket[amount];

        for(int i = 0; i < amount; i++){
            tickets[i] = new Ticket(user_id, Ticket.TicketType.values()[random.nextInt(Ticket.TicketType.values().length)], new Date(System.currentTimeMillis()));
        }

        return tickets;
    }
}
