package Homework4.users;

import Homework4.Ticket;

public class Client extends User{

    public Client (){
        super("Client");
    }

    public Client(Ticket ticket) {
        super("Client", ticket);
    }

    public void shareTicket(String phoneNumber, Ticket ticket, User user){

        if (user.getTicket() == null){
            user.setTicket(this.ticket);
            System.out.println("Ticket " + ticket.getId() + " was shared via phonenumber " + phoneNumber);
        }
        else{
            System.out.println("User with phonenumber " + phoneNumber + "  already have his own ticket!");
        }
    }

    public void shareTicket(String phoneNumber, String email, Ticket ticket, User user){

        if (user.getTicket() == null){
            user.setTicket(this.ticket);
            System.out.println("Ticket " + ticket.getId() + " was shared via phonenumber and email "
                    + phoneNumber + " // " + email);
        }
        else{
            System.out.println("User with phonenumber " + phoneNumber + " and email " + email
                    + " already have his own ticket!");
        }
    }

    @Override
    public void printRole(){
        System.out.println("User role is: " + super.role);
    }
}
