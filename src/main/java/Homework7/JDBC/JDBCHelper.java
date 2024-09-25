package Homework7.JDBC;

import java.sql.*;

public class JDBCHelper {

    public static final String URL         = "jdbc:postgresql://localhost/my_ticket_service_db";
    public static final String USERNAME    = "postgres";
    public static final String PASSWORD    = "****";

    private static Connection connection;

    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
