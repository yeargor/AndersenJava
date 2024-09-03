import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TicketService {
    public static void main(String[] args) {

        Ticket[] tickets = generateTickets(10);

        for (Ticket ticket : tickets){
            System.out.println(ticket.toString());
        }
        System.out.println("Founded ticket: " + findByID(tickets, "3").toString());

        List<Ticket> ticketsForSectorA = getTicketsForSector(tickets, Sector.A);

        System.out.println("Tickets for sector A: ");
        for (Ticket ticket : ticketsForSectorA) {
            System.out.println(ticket);
        }
    }

    public static List<Ticket> getTicketsForSector(Ticket[] tickets, Sector sector) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getStadiumSector() == sector) {
                result.add(ticket);
            }
        }
        return result;
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
