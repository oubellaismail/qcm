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
import com.json.DAOimpl.QuestionDAOimp;
import com.json.DAO.QuestionDAO;
import com.json.model.Question;

public class QuizDAOimp implements QuizDAO {

    public int count = 1;


    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";
    public String jdbcUser = "root";
    public String jdbcPassword = "";

    private static final String INSERT_QUIZ_SQL = "INSERT INTO Quiz " +
        "(Q1, Q2, Q3, Q4, Q5) VALUES " +
        "(?, ?, ?, ?, ?) ;";

    private static final String SELECT_QUIZ_BY_ID = "SELECT * FROM Quiz WHERE id = ? ;" ;

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

            QuestionDAO questionDAO = new QuestionDAOimp();
            List<Question> questions = quiz.getQuestions();            

            quizStatement.setInt(1, questionDAO.insertQuestion(questions.get(0)));
            quizStatement.setNull(2, java.sql.Types.INTEGER);
            quizStatement.setNull(3, java.sql.Types.INTEGER);
            quizStatement.setNull(4, java.sql.Types.INTEGER);
            quizStatement.setNull(5, java.sql.Types.INTEGER);

            // quizStatement.setInt(2, null);
            // quizStatement.setInt(3, null);
            // quizStatement.setInt(4, null);
            // quizStatement.setInt(5, null);

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
                    List<Question> questionList = new ArrayList<Question>();

                    for (int i = 1; i <= count; i++) {
                        int questionId = resultSet.getInt("Q" + String.valueOf(i));
                        questionList.add(new QuestionDAOimp().findQuestion(questionId));
                    } 

                    quiz = new Quiz(id, questionList);
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
                    int id = resultSet.getInt("id");
                    List<Question> questionList = new ArrayList<Question>();
                    for (int i = 1; i <= count; i++) {
                        int questionId = resultSet.getInt("Q" + String.valueOf(id));
                        questionList.add(new QuestionDAOimp().findQuestion(questionId));
                    } 

                    quizList.add(new Quiz(id, questionList));
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
