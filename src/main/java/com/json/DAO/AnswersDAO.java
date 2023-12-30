package com.json.DAO;

import com.json.model.Answers;

public interface AnswersDAO {
    int insertAnswers(Answers answers);
    Answers findAnswers(int id);
    void deleteAnswers(int id);
}
