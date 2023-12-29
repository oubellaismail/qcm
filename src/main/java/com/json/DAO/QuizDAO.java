package com.json.DAO;

import java.util.List;

import com.json.model.Quiz;
public interface QuizDAO {
    void insertQuiz(Quiz quiz);
    Quiz findQuiz(int id);
    List<Quiz> findAll();
    void deleteQuiz(int id);
}
