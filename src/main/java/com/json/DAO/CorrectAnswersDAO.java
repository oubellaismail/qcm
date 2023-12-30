package com.json.DAO;


import com.json.model.CorrectAnswers;

public interface CorrectAnswersDAO {
    int insertCorrectAnswers(CorrectAnswers correctAnswers);
    CorrectAnswers findCorrectAnswers(int id);
    void deleteCorrectAnswers(int id);
}
