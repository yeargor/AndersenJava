package Homework10.repository;

import Homework10.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class UserRepository {

    private final SessionFactory sessionFactory;

    @Transactional
    public User getUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, userId);
    }
}
