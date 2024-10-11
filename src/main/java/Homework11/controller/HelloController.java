package Homework11.controller;

import Homework11.model.Ticket;
import Homework11.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final TicketRepository ticketRepository;

    @GetMapping("/protected")
    public Optional<Ticket> protectedEndpoint(){
        return ticketRepository.findById(5);
    }
}
