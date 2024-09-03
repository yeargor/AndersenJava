package Homework1;

import java.util.Objects;
import java.util.Random;

public class TicketService {
    public static void main(String[] args) {

        Ticket[] tickets = generateTickets(10);

        for (Ticket ticket : tickets){
            System.out.println(ticket.toString());
        }
        System.out.println("Founded ticket: " + findByID(tickets, "3").toString());

        System.out.println("Creating some merge conflicts))");
        System.out.println("Creating some merge conflicts))");
        System.out.println("Creating some merge conflicts))");
        System.out.println("Creating some merge conflicts))");
        System.out.println("Creating some merge conflicts))");
        System.out.println("Creating some merge conflicts))");
    }

    private static Ticket[] generateTickets(int len){

        Random random = new Random();
        Ticket[] tickets = new Ticket[len];

        for (int i = 0; i < len; i++){

            String concertHall = "hall#" + i;
            int eventCode = random.nextInt(900)+100;
            long unixTime = System.currentTimeMillis() / 1000L;
            boolean isPromo = random.nextBoolean();
            Sector sector = Sector.values()[random.nextInt(Sector.values().length)];
            float allowedWeight = random.nextFloat() * 10;

            tickets[i] = new Ticket(String.valueOf(i), concertHall, eventCode, unixTime, isPromo, sector, allowedWeight);

        }

        return tickets;
    }

    private static Ticket findByID(Ticket[] tickets, String id){

        for (Ticket ticket : tickets){
            if (Objects.equals(ticket.getId(), id)){
                return ticket;
            }
        }

        return null;
    }
}
