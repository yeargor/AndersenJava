package Homework7.dao.implementation;

import Homework7.dao.IDao;
import Homework7.entities.User;

import java.sql.*;

public class UserDao implements IDao<User> {

    private Connection connection;

    private static final String INSERT_QUERY = "INSERT INTO users (name, creationdate) VALUES (?, ?)";

    private static final String UPDATE_QUERY = "UPDATE users SET name = ?, creationdate = ? WHERE id = ?";

    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";

    public UserDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(User user) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setDate(2, user.getCreationDate());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    user.setId(id);
                }
            } else {
                System.out.println("No rows affected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, user.getName());
            statement.setDate(2, user.getCreationDate());
            statement.setInt(3, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int userId = result.getInt("id");
                    String name = result.getString("name");
                    Date creationDate = result.getDate("creationdate");

                    return new User(userId, name, creationDate);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
