package com.json.model;
import java.util.*;
import java.util.Objects;
public class Quiz {
    private int id;
    private List<Question> questions;
    private int userId;

    public Quiz() {
    }

    public Quiz(int userId) {
        this.userId = userId;
        this.questions = new ArrayList<Question>();
    }
    
    public Quiz(int id, List<Question> questions, int userId) {
        this.id = id;
        this.questions = questions;
        this.userId = userId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Quiz id(int id) {
        setId(id);
        return this;
    }

    public Quiz questions(List<Question> questions) {
        setQuestions(questions);
        return this;
    }

    public Quiz userId(int userId) {
        setUserId(userId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Quiz)) {
            return false;
        }
        Quiz quiz = (Quiz) o;
        return id == quiz.id && Objects.equals(questions, quiz.questions) && userId == quiz.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questions, userId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", questions='" + getQuestions() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }

    
}
