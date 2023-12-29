package com.json.DAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.json.DAO.AnswersDAO;
import com.json.model.Answers;

public class AnswersDAOimpl implements AnswersDAO {
    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";
    public String jdbcUser = "ismail";
    public String jdbcPassword = "just";

    private static final String INSERT_ANSWERS_SQL = "INSERT INTO ANSWERS " +
            "(answer_a, answer_b, answer_c, answer_d, answer_e, answer_f) VALUES " +
            "(?, ?, ?, ?, ?) ;";

    private static final String SELECT_ANSWERS_BY_ID = "SELECT FROM ANSWERS WHERE id = ? ;";

    private static final String DELETE_ANSWERS_SQL = "DELETE FROM ANSWERS WHERE id = ? ;";

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
    
    public void insertAnswers(Answers answers) {
        try (Connection connection = getConnection()) {
            PreparedStatement answersStatement = connection.prepareStatement(INSERT_ANSWERS_SQL);

            answersStatement.setString(1, answers.getAnswer_a());
            answersStatement.setString(2, answers.getAnswer_b());
            answersStatement.setString(3, answers.getAnswer_c());
            answersStatement.setString(4, answers.getAnswer_d());
            answersStatement.setString(5, answers.getAnswer_e());
            answersStatement.setString(5, answers.getAnswer_f());

            answersStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Answers findAnswers(int id) {
        Answers answers = null;
        try (Connection connection = getConnection()) {
            PreparedStatement answersStatement = connection.prepareStatement(SELECT_ANSWERS_BY_ID);
            answersStatement.setInt(1, id);

            try (ResultSet resultSet = answersStatement.executeQuery()) {
                while (resultSet.next()) {
                    String answer_a = resultSet.getString("answer_a");
                    String answer_b = resultSet.getString("answer_b");
                    String answer_c = resultSet.getString("answer_c");
                    String answer_d = resultSet.getString("answer_d");
                    String answer_e = resultSet.getString("answer_e");
                    String answer_f = resultSet.getString("answer_f");

                    answers = new Answers(id, answer_a, answer_b, answer_c, answer_d, answer_e, answer_f);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answers;
    }

    public void deleteAnswers(int id) {
        try (Connection connection = getConnection();) {
            PreparedStatement questionStatement = connection.prepareStatement(DELETE_ANSWERS_SQL);

            questionStatement.setInt(1, id);
            questionStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
