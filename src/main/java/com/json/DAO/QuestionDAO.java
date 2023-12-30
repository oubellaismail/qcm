package com.json.DAO;
import java.util.List;

import com.json.model.Question;

public interface QuestionDAO {
    int insertQuestion(Question question);
    Question findQuestion(int id);
    List<Question> findAll();
    void deleteQuestion(int id);
}
