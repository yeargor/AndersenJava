package Homework4.users;

import Homework4.Ticket;

public class Admin extends User{

    public Admin(Ticket ticket) {
        super("Admin", ticket);
    }

    public boolean checkTicket(Ticket ticket, User user){
        return ticket.equals(user.ticket);
    }

    @Override
    public void printRole(){
        System.out.println("User role is: " + super.role);
    }
}
