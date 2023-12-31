package com.json.DAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.json.DAO.CorrectAnswersDAO;
import com.json.model.CorrectAnswers;

public class CorrectAnswersDAOimp implements CorrectAnswersDAO {
    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";

    public String jdbcUser = "ismail";
    public String jdbcPassword = "just";

    // public String jdbcUser = "root";
    // public String jdbcPassword = "";

    private static final String INSERT_CORRECT_ANSWERS_SQL = "INSERT INTO CORRECT_ANSWERS " +
            "(answer_a_correct, answer_b_correct, answer_c_correct, answer_d_correct, answer_e_correct, answer_f_correct) VALUES " +
            "(?, ?, ?, ?, ?, ?) ;";

    private static final String SELECT_CORRECT_ANSWERS_BY_ID = "SELECT * FROM CORRECT_ANSWERS WHERE id = ? ;";

    private static final String DELETE_CORRECT_ANSWERS_SQL = "DELETE FROM CORRECT_ANSWERS WHERE id = ? ;";

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
    
    public int insertCorrectAnswers(CorrectAnswers correctAnswers) {
        try (Connection connection = getConnection()) {
            PreparedStatement answersStatement = connection.prepareStatement(INSERT_CORRECT_ANSWERS_SQL,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            answersStatement.setBoolean(1, correctAnswers.getAnswer_a_correct());
            answersStatement.setBoolean(2, correctAnswers.getAnswer_b_correct());
            answersStatement.setBoolean(3, correctAnswers.getAnswer_c_correct());
            answersStatement.setBoolean(4, correctAnswers.getAnswer_d_correct());
            answersStatement.setBoolean(5, correctAnswers.getAnswer_e_correct());
            answersStatement.setBoolean(6, correctAnswers.getAnswer_f_correct());

            answersStatement.executeUpdate();
            
            try (ResultSet generatedKeys = answersStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating Answers failed, no ID obtained.");

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public CorrectAnswers findCorrectAnswers(int id) {
        CorrectAnswers correctAnswers = null;
        try (Connection connection = getConnection()) {
            PreparedStatement answersStatement = connection.prepareStatement(SELECT_CORRECT_ANSWERS_BY_ID);
            answersStatement.setInt(1, id);

            try (ResultSet resultSet = answersStatement.executeQuery()) {
                while (resultSet.next()) {
                    Boolean answer_a_correct = resultSet.getBoolean("answer_a_correct");
                    Boolean answer_b_correct = resultSet.getBoolean("answer_b_correct");
                    Boolean answer_c_correct = resultSet.getBoolean("answer_c_correct");
                    Boolean answer_d_correct = resultSet.getBoolean("answer_d_correct");
                    Boolean answer_e_correct = resultSet.getBoolean("answer_e_correct");
                    Boolean answer_f_correct = resultSet.getBoolean("answer_f_correct");

                    correctAnswers = new CorrectAnswers(id, answer_a_correct, answer_b_correct, answer_c_correct, answer_d_correct, answer_e_correct, answer_f_correct);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return correctAnswers;
    }

    public void deleteCorrectAnswers(int id) {
        try (Connection connection = getConnection();) {
            PreparedStatement questionStatement = connection.prepareStatement(DELETE_CORRECT_ANSWERS_SQL);

            questionStatement.setInt(1, id);
            questionStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
