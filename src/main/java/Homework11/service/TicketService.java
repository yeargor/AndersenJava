package Homework11.service;
import Homework11.model.Ticket;
import Homework11.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    
    private final TicketRepository ticketRepository;

    public Ticket findById(Integer id) {
        return ticketRepository.findById(id).orElse(null);
    }
}