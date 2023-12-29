package model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private String title;
    private String description;
    private List<Question> questions;
    private int professorId;

    
    public Quiz(int id, String title, String description, int professorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.professorId = professorId;
        questions = new ArrayList<Question>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
    
    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
