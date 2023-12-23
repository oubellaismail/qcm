package DAO;
import java.util.*;
import model.Question;
public interface QuestionDAO {
    public void insertQuestion(Question question);
    public Question findQuestion(int id);
    public List<Question> findAll();
    public void updateQuestion(Question question);
    public void deleteQuestion(int id);
}
