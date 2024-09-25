package Homework7.dao;

import Homework7.JDBC.JDBCHelper;
import Homework7.dao.implementation.TicketDao;
import Homework7.dao.implementation.UserDao;

public class DaoFactory {

    public static TicketDao createTicketDao(){
        return new TicketDao(JDBCHelper.getConnection());
    }

    public static UserDao createUserDao(){
        return new UserDao(JDBCHelper.getConnection());
    }
}
