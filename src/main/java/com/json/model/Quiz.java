package com.json.model;
import java.util.*;
import java.util.Objects;
public class Quiz {
    private int id;
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(int id, List<Question> questions) {
        this.id = id;
        this.questions = questions;
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

    public Quiz id(int id) {
        setId(id);
        return this;
    }

    public Quiz questions(List<Question> questions) {
        setQuestions(questions);
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
        return id == quiz.id && Objects.equals(questions, quiz.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questions);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", questions='" + getQuestions() + "'" +
            "}";
    }
    
}
