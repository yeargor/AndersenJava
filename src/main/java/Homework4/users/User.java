package Homework4.users;

import Homework4.ClassTemplate;
import Homework4.Ticket;

public abstract class User extends ClassTemplate {

    protected String role;
    protected Ticket ticket;

    public User(String role){
        this.role = role;
    }

    public User(String role, Ticket ticket) {
        this.role = role;
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public abstract void printRole();
}
