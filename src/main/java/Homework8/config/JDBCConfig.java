package Homework8.config;

import Homework8.model.Ticket;
import Homework8.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JDBCConfig {

    public static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";

    public static SessionFactory getSessionFactory(){
        return new Configuration()
                .configure(HIBERNATE_CFG_XML)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public static Session getSession(){ return getSessionFactory().getCurrentSession(); }
}
