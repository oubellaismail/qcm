package com.json.DAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.json.DAO.UserDAO;
import com.json.model.User;
import com.json.DAOimpl.QuizDAOimp;

public class UserDAOimp implements UserDAO {
    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";

    public String jdbcUser = "ismail";
    public String jdbcPassword = "just";

    // public String jdbcUser = "root";
    // public String jdbcPassword = "";

    private static final String INSERT_USER_SQL = "INSERT INTO USERS " +
            "(userName, email, password) VALUES " +
            "(?, ?, ?) ;";

    private static final String SELECT_USER_BY_EMAIL_PASSWORD = "SELECT * FROM USERS WHERE email = ? and password = ? ;";

    private static final String UPDATE_USER_SQL = "UPDATE USERS SET userNmae = ?, email = ?, password = ?, level =? WHERE id = ?;";

    private static final String DELETE_USER_SQL = "DELETE FROM USERS WHERE id = ? ;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void inserUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);

            userStatement.setString(1, user.getuserName());
            userStatement.setString(2, user.getEmail());
            userStatement.setString(3, user.getPassword());

            userStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement userStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_PASSWORD);

            userStatement.setString(1, user.getEmail());
            userStatement.setString(2, user.getPassword());

            try (ResultSet resultSet = userStatement.executeQuery()){
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setuserName(resultSet.getString("userName"));
                    user.setLevel(resultSet.getInt("level"));

                    return user;
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateUser(User user) {
        try (Connection connection = getConnection();) {
            PreparedStatement userStatement = connection.prepareStatement(UPDATE_USER_SQL);
            userStatement.setString(1, user.getuserName());
            userStatement.setString(2, user.getEmail());
            userStatement.setString(3, user.getPassword());
            userStatement.setInt(4, user.getLevel());

            userStatement.setInt(5, user.getId());
            userStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try (Connection connection = getConnection();) {
            PreparedStatement userStatement = connection.prepareStatement(DELETE_USER_SQL);

            userStatement.setInt(1, id);
            userStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
