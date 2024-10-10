package Homework10.repository;

import Homework10.model.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketJsonMapper {
    @Value("${tickets.path}")
    Resource ticketsFile;
    ObjectMapper objectMapper;

    public TicketJsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Ticket> loadTicketsFromFile() throws IOException {
        return objectMapper.readValue(
                ticketsFile.getInputStream(),
                new TypeReference<List<Ticket>>() {}
        );
    }
}
