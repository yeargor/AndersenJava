package Homework7.dao.implementation;

import Homework7.JDBC.JDBCHelper;
import Homework7.dao.IDao;
import Homework7.entities.User;

import java.sql.*;

public class UserDao implements IDao<User> {

    private Connection connection;

    public UserDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(User user) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "INSERT INTO \"User\" "
                            + "(name, creation_date) "
                            + "VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getName());
            statement.setDate(2, user.getCreation_date());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0){
                ResultSet result = statement.getGeneratedKeys();
                if(result.next()){
                    int id = result.getInt(1);
                    user.setId(id);
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
    public void update(User user) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "UPDATE \"User\"  "
                            + "SET name = ?, creation_date = ? "
                            + "WHERE id = ?");

            statement.setString(1, user.getName());
            statement.setDate(2, user.getCreation_date());

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
            statement = connection.prepareStatement("DELETE FROM \"User\" WHERE id = ?");
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
    public User findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM \"User\" WHERE id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                int userId = result.getInt("id");
                String name = result.getString("name");
                Date creationDate = result.getDate("creation_date");

                return new User(userId, name, creationDate);
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
}
