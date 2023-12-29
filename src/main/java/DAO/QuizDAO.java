package DAO;
import model.Quiz;
import java.util.List;
public interface QuizDAO {
    public void insertQuiz(int id, Quiz quiz);
    public Quiz findQuiz(int id);
    public List<Quiz> findAllQuizs();
    public void updateQuiz(Quiz quiz);
    public void deleteQuiz(int id);

    
    
}
