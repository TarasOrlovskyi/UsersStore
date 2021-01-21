package orlovskyi.web.database;

import orlovskyi.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsersTableDataBase {
    public final String NAME_USER = "postgres";
    public final String PASSWORD = "ivasyutyak";
    public final String URL = "jdbc:postgresql://localhost:5432/usersstore";

    public final String INSERT_USER = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
    public final String DEL_USER = "DELETE FROM users WHERE user_id=?";
    public final String EDIT_USER = "UPDATE users SET salary=?, last_name=?, first_name=?, birth=? WHERE user_id=?";
    public final String SELECT_ALL_USERS = "SELECT * FROM users";
    public final String SELECT_NEXT_USER_ID = "SELECT MAX(user_id) FROM users";

    public void addUserToDataBase(User user) {
        try (Connection connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)){
            preparedStatement.setLong(1, user.getUserId());
            preparedStatement.setDouble(2, user.getSalary());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setDate(5, Date.valueOf(user.getBirth()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserFromDataBase(User user) {
        try (Connection connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DEL_USER)){
            preparedStatement.setDouble(1, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUserIntoDataBase(User user) {
        try (Connection connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER)){
            preparedStatement.setDouble(1, user.getSalary());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setDate(4, Date.valueOf(user.getBirth()));
            preparedStatement.setLong(5, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> selectAllUsersFromDataBase() {
        List<User> userList = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setSalary(resultSet.getDouble("salary"));
                user.setBirth(resultSet.getDate("birth").toLocalDate());
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public int getNextUserId(){
        int userId = 0;
        try (Connection connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_NEXT_USER_ID)){
            resultSet.next();
            userId = resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userId + 1;
    }
}
