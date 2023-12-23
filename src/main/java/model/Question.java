package model;

import java.util.List;

public class Question {
    private int id;
    private String text;
    private int quizId;
    private List<Option> options;

    public Question(int id, String text, int quizId, List<Option> options) {
        this.id = id;
        this.text = text;
        this.quizId = quizId;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
