package Homework4;

import Homework4.users.Admin;
import Homework4.users.Client;
import Homework4.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TicketService {

    private static List<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) {

        tickets = generateTickets(tickets, 10);
        System.out.println("Founded ticket: " + findByID(tickets, "3").toString());

        String phone = "1234567890";
        String email = "egor.senkevich@gmail.com";
        Client client1 = new Client(tickets.getLast());
        Client client2 = new Client();
        Admin admin1 = new Admin(tickets.get(0));

        System.out.println("Checking clients ticket: " + admin1.checkTicket(tickets.get(2), client2));

        //static polymorphism
        client1.shareTicket(phone, client1.getTicket(), client2);
        client1.shareTicket(phone, email, client1.getTicket(), client2);

        //dynamic polymorphism
        client1.printRole();
        admin1.printRole();

        //other student code
        List<Ticket> ticketsForSectorA = getTicketsForSector(tickets, Sector.A);

        System.out.println("Tickets for sector A: ");
        for (Ticket ticket : ticketsForSectorA) {
            System.out.println(ticket);
        }
    }

    //other student method
    public static List<Ticket> getTicketsForSector(List<Ticket> tickets, Sector sector) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getStadiumSector() == sector) {
                result.add(ticket);
            }
        }
        return result;
    }

    public static List<Ticket> generateTickets(List<Ticket> tickets, int len){

        Random random = new Random();

        for (int i = 0; i < len; i++){

            String concertHall = "hall#" + i;
            int eventCode = random.nextInt(900)+100;
            long unixTime = System.currentTimeMillis() / 1000L;
            boolean isPromo = random.nextBoolean();
            Sector sector = Sector.values()[random.nextInt(Sector.values().length)];
            float allowedWeight = random.nextFloat() * 10;

            tickets.add(new Ticket(String.valueOf(i), concertHall, eventCode, unixTime, isPromo, sector, allowedWeight));

        }

        return tickets;
    }

    private static Ticket findByID(List<Ticket> tickets, String id){

        for (Ticket ticket : tickets){
            if (Objects.equals(ticket.getId(), id)){
                return ticket;
            }
        }

        return null;
    }
}
