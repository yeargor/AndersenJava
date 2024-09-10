package Homework5;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import tools.adapters.LocalDateAdapter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusTicketService {

    public static void main(String[] args) {
        try {
            BusTicketGenerator busticketGenerator = new BusTicketGenerator(30);
            serializeBusTickets(busticketGenerator.getBusTickets());

            List<BusTicket> busTickets = deserializeBusTickets("busTickets.json");

            for (Object i : busTickets) {
                System.out.println(i.toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void serializeBusTickets(List<BusTicket> busTickets){

        Gson gson = new GsonBuilder().serializeNulls().create();

        try {
            FileWriter writer = new FileWriter("busTickets.json");
            gson.toJson(busTickets, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<BusTicket> deserializeBusTickets(String fileName) throws FileNotFoundException {

        FileReader reader = new FileReader(fileName);
        Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        List<BusTicket> tickets = gson.fromJson(reader, new TypeToken<ArrayList<BusTicket>>() {}.getType());

        return tickets;
    }
}
