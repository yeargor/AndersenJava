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
            BusTicketSerializer busTicketSerializer = new BusTicketSerializer();

            busTicketSerializer.serializeBusTickets(busticketGenerator.getBusTickets());

            List<BusTicket> busTickets = busTicketSerializer.deserializeBusTickets("busTickets.json");

            for (BusTicket i : busTickets) {
                System.out.println(i.toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
