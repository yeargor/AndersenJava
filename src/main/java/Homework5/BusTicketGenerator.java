package Homework5;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BusTicketGenerator {

    private List<BusTicket> busTickets;

    public BusTicketGenerator(int len) {
        this.busTickets = generateBusTickets(len);
    }

    public List<BusTicket> getBusTickets() {
        return busTickets;
    }

    private List<BusTicket> generateBusTickets(int len){

        Random random = new Random();
        List<BusTicket> busTickets = new ArrayList<>();

        String[] ticketClasses = {"STD","CLA", null};
        String[] ticketTypes = {"DAY", "WEEK", "MONTH", "YEAR", "PRIME", null};
        List<LocalDate> dates = generateDate(len);
        int[] prices = generatePrices(len);

        for (int i = 0; i < len; i++){

            String ticketClass = ticketClasses[random.nextInt(ticketClasses.length)];
            String ticketType = ticketTypes[random.nextInt(ticketTypes.length)];
            LocalDate date = dates.get(i);
            int price = prices[i];

            BusTicket busTicket = BusTicket.createBusTicket(ticketClass, ticketType, date, price);
            if (busTicket != null) {
                busTickets.add(busTicket);
            }
        }

        return busTickets;
    }

    private int[] generatePrices(int len){

        Random random = new Random();
        int[] prices = new int[len];

        for (int i = 0; i < len; i++) {
            prices[i] = random.nextInt(1000);
        }

        return prices;
    }

    private List<LocalDate> generateDate(int len){

        Random random = new Random();
        List<LocalDate> dates = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            int wayToChoose = random.nextInt(3);
            switch (wayToChoose) {
                case 0:
                    dates.add(null);
                    break;
                case 1:
                    LocalDate futureDate = LocalDate.now().plus(random.nextInt(10) + 1, ChronoUnit.YEARS);
                    dates.add(futureDate);
                    break;
                case 2:
                    dates.add(LocalDate.now());
                    break;
            }
        }
        return dates;
    }
}
