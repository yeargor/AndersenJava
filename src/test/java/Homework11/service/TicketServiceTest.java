package Homework11.service;

import Homework11.model.Ticket;
import Homework11.model.Ticket.TicketType;
import Homework11.model.User;
import Homework11.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;
    private Ticket ticket;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1, "John", LocalDate.now(), new HashSet<>(), true);
        ticket = new Ticket(1, user, TicketType.DAY, LocalDate.now());
    }

    @Test
    void findById_whenIdExists_returnTicket() {
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        Ticket result = ticketService.findById(1);
        assertThat(ticket).usingRecursiveComparison().isEqualTo(result);
        verify(ticketRepository).findById(1);
    }

    @Test
    void findById_whenIdDoesNotExist_returnNull() {
        when(ticketRepository.findById(1)).thenReturn(Optional.empty());
        Ticket result = ticketService.findById(1);
        assertNull(result);
        verify(ticketRepository).findById(1);
    }

    @Test
    void findById_whenIdIsMaxValue_returnNull() {
        when(ticketRepository.findById(Integer.MAX_VALUE)).thenReturn(Optional.empty());
        Ticket result = ticketService.findById(Integer.MAX_VALUE);
        assertNull(result);
        verify(ticketRepository).findById(Integer.MAX_VALUE);
    }

    @Test
    void save_whenIdExists_returnTicket() {
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        Ticket result = ticketService.save(ticket);
        assertThat(ticket).usingRecursiveComparison().isEqualTo(result);
        verify(ticketRepository).save(ticket);
    }

    @Test
    void save_whenIdDoesNotExist_returnNull() {
        when(ticketRepository.save(null)).thenThrow(new IllegalArgumentException("Ticket cannot be null"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ticketService.save(null));
        assertEquals("Ticket cannot be null", exception.getMessage());
        verify(ticketRepository).save(null);
    }

    @Test
    void save_whenIdIsMaxValue_returnNull() {
        Ticket ticket = new Ticket(Integer.MAX_VALUE, user, TicketType.DAY, LocalDate.now());
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        Ticket result = ticketService.save(ticket);
        assertThat(ticket).usingRecursiveComparison().isEqualTo(result);
        verify(ticketRepository).save(ticket);
    }
}