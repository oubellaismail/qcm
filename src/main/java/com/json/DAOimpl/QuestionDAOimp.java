package com.json.DAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.json.model.CorrectAnswers;
import com.json.model.Question;
import com.json.DAO.QuestionDAO;
import com.json.DAOimpl.AnswersDAOimp;
import com.json.DAO.CorrectAnswersDAO;
import com.json.DAOimpl.CorrectAnswersDAOimp;
import com.json.DAOimpl.TagDAOimp;
import com.json.model.Tag;

public class QuestionDAOimp implements QuestionDAO{
    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";
    public String jdbcUser = "ismail";
    public String jdbcPassword = "just";

    //! question id will not be auto increment !

    private static final String INSERT_QUESTION_SQL = "INSERT INTO QUESTION " + 
        "(id, question, description, multiple_correct_answers, correct_answer, explanation, tip, category, difficulty, answers_id, correct_answers_id, tag_id)" + 
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
    ;

    private static final String SELECT_QUESTION_BY_ID = "SELECT * FROM QUESTION WHERE id = ? ;";

    private static final String SELECT_QUESTIONS_SQL = "SELECT * from QUESTION ;" ;

    private static final String DELETE_QUESTION_SQL = "DELETE FROM QUESTION WHERE id = ? ;";

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

    
    public int insertQuestion(Question question){

        try (Connection connection = getConnection()) {
            Question exestingQuestion = findQuestion(question.getId());
            if (exestingQuestion != null) {
                return question.getId();
            } else {                
                PreparedStatement questionStatement = connection.prepareStatement(INSERT_QUESTION_SQL);
    
                questionStatement.setInt(1, question.getId());
                questionStatement.setString(2, question.getQuestion());
                questionStatement.setString(3, question.getDescription());
                questionStatement.setBoolean(4, question.getMultiple_correct_answers());
                questionStatement.setString(5, question.getCorrect_answer());
                questionStatement.setString(6, question.getExplanation());
                questionStatement.setString(7, question.getTip());
                questionStatement.setString(8, question.getCategory());
                questionStatement.setString(9, question.getDifficulty());
                 
    
                questionStatement.setInt(10, new AnswersDAOimp().insertAnswers(question.getAnswers())); //? answers_id
                questionStatement.setInt(11, new CorrectAnswersDAOimp().insertCorrectAnswers(question.getCorrect_answers())); //? correct_answers_id
                questionStatement.setInt(12, new TagDAOimp().insertTag(new Tag(question.getTags().getFirst().getName()))); //? tag_id
    
                questionStatement.executeUpdate();
    
                return question.getId();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    
    public Question findQuestion(int id){
        Question question = null;

        try (Connection connection = getConnection()) {
            PreparedStatement questionStatement = connection.prepareStatement(SELECT_QUESTION_BY_ID);
            questionStatement.setInt(1, id);

            try (ResultSet resultSet = questionStatement.executeQuery()) {
                while (resultSet.next()) {
                    String questionText = resultSet.getString("question");
                    String description = resultSet.getString("description");
                    Boolean multipleCorrectAnswers = resultSet.getBoolean("multiple_correct_answers");
                    String correctAnswer = resultSet.getString("correct_answer");
                    String explanation = resultSet.getString("explanation");
                    String tip = resultSet.getString("tip");
                    String category = resultSet.getString("category");
                    String difficulty = resultSet.getString("difficulty");

                    int AnswersId = resultSet.getInt("answers_id");
                    int correctAnswerId = resultSet.getInt("correct_answers_id");
                    int tagId = resultSet.getInt("tag_id");

                    List <Tag> tagList = new ArrayList<Tag>();
                    tagList.add(new TagDAOimp().findTag(tagId));
                    
                    question = new Question (
                        id,
                        questionText,
                        description,
                        new AnswersDAOimp().findAnswers(AnswersId),
                        multipleCorrectAnswers,
                        new CorrectAnswersDAOimp().findCorrectAnswers(correctAnswerId),
                        correctAnswer,
                        explanation,
                        tip,
                        tagList,
                        category,
                        difficulty
                    );
                }
            }

        }

        catch (SQLException e) {

            //? If resultSet.next() returns false, we catch the SQLException and check if the SQL state is S1000, which is commonly used to indicate that no rows were returned.

            if ("S1000".equals(e.getSQLState())) {
                // No rows were returned, so the ID does not exist in the database
                // Return null to indicate that the question was not found
                return null;
            }
            e.printStackTrace();
        }

        return question;
    }
    
    
    public List<Question> findAll(){
        List<Question> questionList = new ArrayList<Question>();

        try (Connection connection = getConnection()) {
            PreparedStatement questionStatement = connection.prepareStatement(SELECT_QUESTIONS_SQL);
            try (ResultSet resultSet = questionStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String questionText = resultSet.getString("question");
                    String description = resultSet.getString("description");
                    Boolean multipleCorrectAnswers = resultSet.getBoolean("multiple_correct_answers");
                    String correctAnswer = resultSet.getString("correct_answer");
                    String explanation = resultSet.getString("explanation");
                    String tip = resultSet.getString("tip");
                    String category = resultSet.getString("category");
                    String difficulty = resultSet.getString("difficulty");

                    int AnswersId = resultSet.getInt("answers_id");
                    int correctAnswerId = resultSet.getInt("correct_answers_id");
                    int tagId = resultSet.getInt("tag_id");


                    List <Tag> tagList = new ArrayList<Tag>();
                    tagList.add(new TagDAOimp().findTag(tagId));
                    
                    questionList.add (
                        new Question (
                            id,
                            questionText,
                            description,
                            new AnswersDAOimp().findAnswers(AnswersId),
                            multipleCorrectAnswers,
                            new CorrectAnswersDAOimp().findCorrectAnswers(correctAnswerId),
                            correctAnswer,
                            explanation,
                            tip,
                            tagList,
                            category,
                            difficulty
                        )
                    );
                }
            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }
    
    
    public void deleteQuestion(int id){
        try (Connection connection = getConnection();) {
            PreparedStatement questionStatement = connection.prepareStatement(DELETE_QUESTION_SQL);

            Question question = findQuestion(id);
            new AnswersDAOimp().deleteAnswers(question.getAnswers().getId());
            new CorrectAnswersDAOimp().deleteCorrectAnswers(question.getCorrect_answers().getId());

            questionStatement.setInt(1, id);
            questionStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
