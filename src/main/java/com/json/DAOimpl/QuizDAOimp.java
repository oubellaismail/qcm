package com.json.DAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.json.DAO.*;
import com.json.model.*;
import com.json.DAOimpl.*;

public class QuizDAOimp implements QuizDAO {

    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";
    public String jdbcUser = "ismail";
    public String jdbcPassword = "just";

    private static final String INSERT_QUIZ_SQL = "INSERT INTO Quiz " +
        "(Q1, Q2, Q3, Q4, Q5) VALUES " +
        "(?, ?, ?, ?, ?) ;";

    private static final String SELECT_QUIZ_BY_ID = "SELECT * FROM QUIZ WHERE id = ? ;" ;

    private static final String SELECT_QUIZ_BY_USER_ID = "SELECT * FROM QUIZ WHERE user_id = ?  ;"; 

    private static final String DELETE_QUIZ_SQL = "DELETE FROM QUIZ WHERE id = ? ;";

    private static final String DELETE_FROM_QUESTION_QUIZ = "DELETE FROM question_quiz WHERE quiz_id = ? ;";

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
            PreparedStatement quizStatement = connection.prepareStatement(INSERT_QUIZ_SQL, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            // PreparedStatement pivotStatement = connection.prepareStatement(INSERT_QUESTION_QUIZ_TABLE);

            quizStatement.setInt(1, quiz.getUserId());
            quizStatement.executeUpdate();

            // int quizId;

            try (ResultSet generatedKeys = quizStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quiz.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating Answers failed, no ID obtained.");
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuiz(Quiz quiz, int start) {
        try (Connection connection = getConnection()) {
            PreparedStatement pivotStatement = connection.prepareStatement(INSERT_QUESTION_QUIZ_TABLE);
            List<Question> questionsSubset = quiz.getQuestions().subList(start, quiz.getQuestions().size());
            int id = quiz.getId();
            for (Question question : questionsSubset) {
                new QuestionDAOimp().insertQuestion(question);
                pivotStatement.setInt(1, question.getId());
                pivotStatement.setInt(2, id);

                pivotStatement.executeUpdate();
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Quiz findQuiz(int id){
        Quiz quiz = null;
        try (Connection connection = getConnection()) {
            PreparedStatement pivotStatement = connection.prepareStatement(SELECT_QUIZ_ID_QUESTION_QUIZ);
            PreparedStatement quizStatement = connection.prepareStatement(SELECT_QUIZ_BY_ID);
            quizStatement.setInt(1, id);
            
            try (ResultSet resultSet = quizStatement.executeQuery()) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    pivotStatement.setInt(1, id);
        
                    try (ResultSet rs = pivotStatement.executeQuery()) {        
                        QuestionDAO questionDAO = new QuestionDAOimp();
                        List <Question> questions = new ArrayList<Question>();
                        while (rs.next()) {
                            int question_id = rs.getInt("question_id");
                            questions.add(questionDAO.findQuestion(question_id));
                        }

                        quiz = new Quiz (id, questions, userId);
                    }
                }
                    
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return quiz;
    }
    
    @Override
    public List<Quiz> findAll(int userId) {
        List<Quiz> quizList = new ArrayList<Quiz>();
        try (Connection connection = getConnection()) {
            PreparedStatement quizStatement = connection.prepareStatement(SELECT_QUIZ_BY_USER_ID);
            quizStatement.setInt(1, userId);

            try (ResultSet resultSet = quizStatement.executeQuery()) {
                while (resultSet.next()) {
                    quizList.add(findQuiz(resultSet.getInt("id")));
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
            PreparedStatement pivotStatement = connection.prepareStatement(DELETE_FROM_QUESTION_QUIZ);

            pivotStatement.setInt(1, id);
            pivotStatement.executeUpdate();

            quizStatement.setInt(1, id);
            quizStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
