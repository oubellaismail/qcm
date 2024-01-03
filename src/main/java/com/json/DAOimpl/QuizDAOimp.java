package com.json.DAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.json.DAO.*;
import com.json.model.*;
import com.json.DAOimpl.*;

public class QuizDAOimp implements QuizDAO {

    public int count = 1;


    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";
    
    public String jdbcUser = "ismail";
    public String jdbcPassword = "just";
    
    
    // public String jdbcUser = "root";
    // public String jdbcPassword = "";

    private static final String INSERT_QUIZ_SQL = "INSERT INTO QUIZ " +
        "(user_id) VALUES " +
        "(?) ;";

    private static final String INSERT_QUESTION_QUIZ_TABLE = "INSERT INTO question_quiz " +
        "(question_id, quiz_id) VALUES " +
        "(?, ?) ;" ;

    private static final String SELECT_QUIZ_ID_QUESTION_QUIZ = "SELECT question_id FROM question_quiz WHERE" +
        "quiz_id = ? ;";

    private static final String SELECT_QUIZ_BY_ID = "SELECT * FROM QUIZ WHERE id = ? ;" ;

    private static final String SELECT_QUIZS_SQL = "SELECT * FROM QUIZ WHERE user_id = ? ;";

    private static final String DELETE_QUIZ_SQL = "DELETE FROM QUIZ WHERE id = ? ;";

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
            PreparedStatement pivotStatement = connection.prepareStatement(INSERT_QUESTION_QUIZ_TABLE);

            quizStatement.setInt(1, quiz.getUserId());
            quizStatement.executeUpdate();

            int quizId;

            try (ResultSet generatedKeys = quizStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quizId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating Answers failed, no ID obtained.");

                }
            }

            QuestionDAO questionDAO = new QuestionDAOimp();
            CombinedQuestionResponse questions = quiz.getQuestions();
            
            for (int i=0; i < 3 ; i++) {
                List<Question> questionsList = null;
                switch (i) {
                    case 0:
                        questionsList = questions.getEasyQuestions();   
                        break;  
                    case 1:
                        questionsList = questions.getMediumQuestions();   
                        break;  
                    case 2:
                        questionsList = questions.getHardQuestions();   
                        break;  
                    default:
                        break;
                }

                for (Question question : questionsList) {
                    int questionId = questionDAO.insertQuestion(question);
                    pivotStatement.setInt(1, questionId);
                    pivotStatement.setInt(2, quizId);

                    pivotStatement.executeUpdate();
                }
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
            pivotStatement.setInt(1, id);

            try (ResultSet resultSet = quizStatement.executeQuery()) {
                while (resultSet.next()) {
                    quiz = new Quiz();
                    int userId = resultSet.getInt("user_id");
                    // List<Question> questionList = new ArrayList<Question>();

                    // for (int i = 1; i <= count; i++) {
                        // int questionId = resultSet.getInt("Q" + String.valueOf(i));
                        // questionList.add(new QuestionDAOimp().findQuestion(questionId));
                    // } 
                }
            }
            
            quizStatement.setInt(1, id);


            try (ResultSet rs = pivotStatement.executeQuery()) {
                List<Question> questionList = new ArrayList<Question>();
                QuestionDAO questionDAO = new QuestionDAOimp();
                while (rs.next()) {
                    int question_id = rs.getInt("question_id");
                    
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
            PreparedStatement quizStatement = connection.prepareStatement(SELECT_QUIZS_SQL);

            quizStatement.setInt(1, userId);

            try (ResultSet resultSet = quizStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    List<Question> questionList = new ArrayList<Question>();
                    for (int i = 1; i <= count; i++) {
                        int questionId = resultSet.getInt("Q" + String.valueOf(id));
                        questionList.add(new QuestionDAOimp().findQuestion(questionId));
                    } 

                    quizList.add(new Quiz(id, questionList, userId));
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

            Quiz quiz = findQuiz(id);

            quizStatement.setInt(1, id);
            quizStatement.executeUpdate();

            QuestionDAO questionDAO = new QuestionDAOimp();

            for (int index = 0; index < count; index++) {
                questionDAO.deleteQuestion(quiz.getQuestions().get(index).getId());
            }


        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
