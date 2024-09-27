package Homework7.dao.implementation;

import Homework7.dao.IDao;
import Homework7.entities.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements IDao<Ticket> {

    private Connection connection;

    private static final String INSERT_QUERY = "INSERT INTO tickets " +
            "(userid, tickettype, creationdate) " +
            "VALUES (?, ?::tickettype, ?)";

    private static final String UPDATE_QUERY = "UPDATE tickets " +
            "SET userid = ?, tickettype = ?::tickettype, creationdate = ? " +
            "WHERE id = ?";

    private static final String DELETE_BY_ID_QUERY = "DELETE FROM tickets " +
            "WHERE id = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM tickets " +
            "WHERE id = ?";

    private static final String FIND_BY_USERID_QUERY = "SELECT * FROM tickets " +
            "WHERE userid = ?";

    private static final String UPDATE_TICKETTYPE_QUERY = "UPDATE tickets " +
            "SET tickettype = ?::tickettype WHERE id = ?";

    public TicketDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Ticket ticket) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY,
                Statement.RETURN_GENERATED_KEYS)){

            statement.setInt(1, ticket.getUserId());
            statement.setString(2, ticket.getTicketTypeName());
            statement.setDate(3, ticket.getCreationDate());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0){
                ResultSet result = statement.getGeneratedKeys();
                if(result.next()){
                    int id = result.getInt(1);
                    ticket.setId(id);
                }
            }else{
                System.out.println("no rows affected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Ticket ticket) {

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)){
            statement.setInt(1, ticket.getUserId());
            statement.setString(2, ticket.getTicketTypeName());
            statement.setDate(3, ticket.getCreationDate());
            statement.setInt(4, ticket.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {

        try(PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket findById(Integer id) {

        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)){
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()){

                if (result.next()) {
                    int ticketId = result.getInt("id");
                    int userId = result.getInt("userid");
                    String ticketTypeString = result.getString("tickettype");
                    Date creationDate = result.getDate("creationdate");

                    Ticket.TicketType ticketType = Ticket.TicketType.valueOf(ticketTypeString.toUpperCase());

                    return new Ticket(ticketId, userId, ticketType, creationDate);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ticket> findByUserId(Integer id){

        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_USERID_QUERY)){
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery()) {
                List<Ticket> tickets = new ArrayList<>();

                while (result.next()) {

                    Ticket ticket = new Ticket();
                    String ticketTypeString = result.getString("tickettype");

                    ticket.setId(result.getInt("id"));
                    ticket.setUserId(result.getInt("userid"));
                    ticket.setTicketType(Ticket.TicketType.valueOf(ticketTypeString.toUpperCase()));
                    ticket.setCreationDate(result.getDate("creationdate"));

                    tickets.add(ticket);
                }

                return tickets;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTicketType(Integer ticketId, String newTicketType) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TICKETTYPE_QUERY)){
            statement.setString(1, newTicketType);
            statement.setInt(2, ticketId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
