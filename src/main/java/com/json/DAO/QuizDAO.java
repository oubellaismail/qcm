package com.json.DAO;

import java.util.List;

import com.json.model.Quiz;
public interface QuizDAO {
    void insertQuiz(Quiz quiz);
    void updateQuiz(Quiz quiz);
    Quiz findQuiz(int id);
    List<Quiz> findAll(int id);
    void deleteQuiz(int id);
}
