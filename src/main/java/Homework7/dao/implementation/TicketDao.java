package Homework7.dao.implementation;

import Homework7.JDBC.JDBCHelper;
import Homework7.dao.IDao;
import Homework7.entities.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements IDao<Ticket> {

    private Connection connection;

    public TicketDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Ticket ticket) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "INSERT INTO \"Ticket\" "
                            + "(user_id, ticket_type, creation_date) "
                            + "VALUES (?, ?::ticket_type, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, ticket.getUser_id());
            statement.setString(2, ticket.getTicketTypeName());
            statement.setDate(3, ticket.getCreation_date());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0){
                ResultSet result = statement.getGeneratedKeys();
                if(result.next()){
                    int id = result.getInt(1);
                    ticket.setId(id);
                }
                JDBCHelper.closeResultSet(result);
            }else{
                System.out.println("no rows affected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            JDBCHelper.closeStatement(statement);
        }
    }

    @Override
    public void update(Ticket ticket) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "UPDATE \"Ticket\"  "
                            + "SET user_id = ?, ticket_type = ?::ticket_type, creation_date = ? "
                            + "WHERE id = ?");

            statement.setInt(1, ticket.getUser_id());
            statement.setString(2, ticket.getTicketTypeName());
            statement.setDate(3, ticket.getCreation_date());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            JDBCHelper.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("DELETE FROM \"Ticket\" WHERE id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            JDBCHelper.closeStatement(statement);
        }
    }

    @Override
    public Ticket findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement("SELECT * " +
                    "FROM \"Ticket\" " +
                    "WHERE id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                int ticketId = result.getInt("id");
                int user_id = result.getInt("user_id");
                String ticketTypeString = result.getString("ticket_type");
                Date creationDate = result.getDate("creation_date");

                Ticket.TicketType ticketType = Ticket.TicketType.valueOf(ticketTypeString.toUpperCase());

                return new Ticket(ticketId, user_id, ticketType, creationDate);
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JDBCHelper.closeStatement(statement);
            JDBCHelper.closeResultSet(result);
        }
    }

    public List<Ticket> findByUserId(Integer id){

        PreparedStatement statement = null;
        ResultSet result = null;

        try{
            statement = connection.prepareStatement("SELECT * " +
                    "FROM \"Ticket\" " +
                    "WHERE user_id = ?");

            statement.setInt(1, id);
            result = statement.executeQuery();

            List<Ticket> tickets = new ArrayList<>();

            while(result.next()){

                Ticket ticket = new Ticket();
                String ticketTypeString = result.getString("ticket_type");

                ticket.setId(result.getInt("id"));
                ticket.setUser_id(result.getInt("user_id"));
                ticket.setTicketType(Ticket.TicketType.valueOf(ticketTypeString.toUpperCase()));
                ticket.setCreation_date(result.getDate("creation_date"));

                tickets.add(ticket);
            }

            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JDBCHelper.closeStatement(statement);
            JDBCHelper.closeResultSet(result);
        }
    }

    public void updateTicketType(Integer ticketId, String newticketType) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "UPDATE \"Ticket\"  "
                            + "SET ticket_type = ?::ticket_type "
                            + "WHERE id = ?");

            statement.setString(1, newticketType);
            statement.setInt(2, ticketId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            JDBCHelper.closeStatement(statement);
        }
    }
}
