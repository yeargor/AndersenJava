package Homework9.config;

import Homework9.model.Ticket;
import Homework9.model.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "Homework9")
public class AppContextConfig {
    private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure(HIBERNATE_CFG_XML)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }
}
