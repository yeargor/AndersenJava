package Homework10.config;

import Homework10.model.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
    @Value("${tickets.file.path}")
    Resource ticketsFile;
    ObjectMapper objectMapper;

    public TicketJsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Ticket> loadTicketsFromFile() throws IOException {
        List<Ticket> tickets = objectMapper.readValue(
                ticketsFile.getInputStream(),
                new TypeReference<List<Ticket>>() {}
        );
        return new ArrayList<>(tickets);
    }
}
