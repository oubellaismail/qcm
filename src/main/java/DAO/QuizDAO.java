package DAO;
import model.Quiz;
import java.util.*;
public interface QuizDAO {
    public void insertQuiz(Quiz quiz);
    public Quiz findQuiz(int id);
    public List<Quiz> findAll();
    public void updateQuiz(Quiz quiz);
    public void deleteQuiz(int id);
}
