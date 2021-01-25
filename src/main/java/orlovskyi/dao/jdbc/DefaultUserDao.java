package orlovskyi.dao.jdbc;

import orlovskyi.PropertyReader;
import orlovskyi.dao.UserDao;
import orlovskyi.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DefaultUserDao implements UserDao {
    private final String jdbcUser;
    private final String jdbcPassword;
    private final String jdbcUrl;

    public DefaultUserDao(){
        PropertyReader propertyReader = new PropertyReader();
        jdbcUser = propertyReader.getJdbcUser();
        jdbcPassword = propertyReader.getJdbcPassword();
        jdbcUrl = propertyReader.getJdbcUrl();
    }
    //private static final String NAME_USER = "postgres";
    //private static final String PASSWORD = "ivasyutyak";
    //public static final String URL = "jdbc:postgresql://localhost:5432/usersstore";

    private static final String INSERT_USER = "INSERT INTO users(salary, last_name, first_name, birth) VALUES(?, ?, ?, ?)";
    private static final String DEL_USER = "DELETE FROM users WHERE user_id=?";
    private static final String EDIT_USER = "UPDATE users SET salary=?, last_name=?, first_name=?, birth=? WHERE user_id=?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users ORDER BY user_id";
    private static final String SELECT_SEARCHED_USERS = "SELECT * FROM users WHERE LOWER(first_name) LIKE LOWER(?) OR LOWER(last_name) LIKE LOWER(?)";

    @Override
    public void addUser(User user) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)){
            fillPrepareStatement(preparedStatement, user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(long userId) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(DEL_USER)){
            preparedStatement.setDouble(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(User user) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER)){
            fillPrepareStatement(preparedStatement, user);
            preparedStatement.setLong(5, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> userList = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()){
            userList = getUsers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> selectSearchedUsers(String searchWord){
        List<User> usersList = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SEARCHED_USERS)){
            preparedStatement.setString(1, "%"+searchWord+"%");
            preparedStatement.setString(2, "%"+searchWord+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            usersList = getUsers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    private List<User> getUsers(ResultSet resultSet){
        List<User> userList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setSalary(resultSet.getDouble("salary"));
                user.setBirth(resultSet.getDate("birth").toLocalDate());
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private void fillPrepareStatement(PreparedStatement preparedStatement, User user) throws SQLException{
        preparedStatement.setDouble(1, user.getSalary());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setDate(4, Date.valueOf(user.getBirth()));
    }
}