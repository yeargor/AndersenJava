package Homework5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import tools.adapters.LocalDateAdapter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusTicketSerializer {

    public void serializeBusTickets(List<BusTicket> busTickets){
        Gson gson = new GsonBuilder().serializeNulls().create();

        try {
            FileWriter writer = new FileWriter("busTickets.json");
            gson.toJson(busTickets, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<BusTicket> deserializeBusTickets(String fileName) throws FileNotFoundException {

        FileReader reader = new FileReader(fileName);
        Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        List<BusTicket> tickets = gson.fromJson(reader, new TypeToken<ArrayList<BusTicket>>() {}.getType());

        return tickets;
    }
}
