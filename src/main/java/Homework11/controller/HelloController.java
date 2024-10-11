package Homework11.controller;

import Homework11.model.Ticket;
import Homework11.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final TicketService ticketService;

    @GetMapping("/protected")
    public Ticket protectedEndpoint(){
        return ticketService.findById(5);
    }
}
