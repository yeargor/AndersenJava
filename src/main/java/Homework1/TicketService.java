package Homework1;

public class TicketService {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        Ticket ticketFilled = new Ticket("1234", "hallnumaon", 223, 1725013233833L, true, Sector.A, 2.3f);
        Ticket ticketLimited = new Ticket("hallnumatw", 244, 1725502500000L);

        //getting filled ticked creation time
        System.out.println(ticketFilled.getCreatedAt());
        //printing out limited ticked fields information
        System.out.println(ticketLimited.toString());
        //getting empty ticket price (which is default also)
        System.out.println(ticket.getPrice());

    }
}
