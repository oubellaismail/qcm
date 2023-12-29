package com.json.DAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.json.DAO.QuizDAO;
import com.json.model.Quiz;

public class QuizDAOimpl implements QuizDAO {
    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";
    public String jdbcUser = "ismail";
    public String jdbcPassword = "just";

    private static final String INSERT_QUIZ_SQL = "INSERT INTO Quiz " +
        "(Q1, Q2, Q3, Q4, Q5) VALUES " +
        "(?, ?, ?, ?, ?) ;";

    private static final String SELECT_QUIZ_BY_ID = "SELECT FROM Quiz WHERE id = ? ;" ;

    private static final String SELECT_QUIZS_SQL = "SELECT * FROM Quiz ;";

    private static final String DELETE_QUIZ_SQL = "DELETE FROM Quiz WHERE id = ? ;";

    protected Connection getConnection (){
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

    @Override
    public void insertQuiz(Quiz quiz) {
        try (Connection connection = getConnection()) {
            PreparedStatement quizStatement = connection.prepareStatement(INSERT_QUIZ_SQL);

            //!
            quizStatement.setInt(1, 0);
            quizStatement.setInt(2, 0);
            quizStatement.setInt(3, 0);
            quizStatement.setInt(4, 0);
            quizStatement.setInt(5, 0);

            quizStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Quiz findQuiz(int id){
        Quiz quiz = null;
        try (Connection connection = getConnection()) {
            PreparedStatement quizStatement = connection.prepareStatement(SELECT_QUIZ_BY_ID);
            quizStatement.setInt(1, id);

            try (ResultSet resultSet = quizStatement.executeQuery()) {
                while (resultSet.next()) {
                    int Q1 = resultSet.getInt("Q1");
                    int Q2 = resultSet.getInt("Q2");
                    int Q3 = resultSet.getInt("Q3");
                    int Q4 = resultSet.getInt("Q4");
                    int Q5 = resultSet.getInt("Q5");

                    //!
                }
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return quiz;
    }
    
    @Override
    public List<Quiz> findAll(){
        List<Quiz> quizList = new ArrayList<Quiz>();
        try (Connection connection = getConnection()) {
            PreparedStatement quizStatement = connection.prepareStatement(SELECT_QUIZS_SQL);
            try (ResultSet resultSet = quizStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id  = resultSet.getInt("id");
                    int Q1 = resultSet.getInt("Q1");
                    int Q2 = resultSet.getInt("Q2");
                    int Q3 = resultSet.getInt("Q3");
                    int Q4 = resultSet.getInt("Q4");
                    int Q5 = resultSet.getInt("Q5");
            
                    //! Missing lines ! 
                }
            }
            
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }

        return quizList;
    }
    
    
    @Override
    public void deleteQuiz(int id){
        try (Connection connection = getConnection();) {
            PreparedStatement quizStatement = connection.prepareStatement(DELETE_QUIZ_SQL);

            //! Missign lines ! 

            quizStatement.setInt(1, id);
            quizStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
