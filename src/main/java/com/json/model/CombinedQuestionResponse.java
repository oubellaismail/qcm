package com.json.model;

import java.util.List;

import java.util.Objects;

public class CombinedQuestionResponse {
    int id;
    private List<Question> easyQuestions;
    private List<Question> mediumQuestions;
    private List<Question> hardQuestions;



    public CombinedQuestionResponse() {
    }

    public CombinedQuestionResponse(int id, List<Question> easyQuestions, List<Question> mediumQuestions, List<Question> hardQuestions) {
        this.id = id;
        this.easyQuestions = easyQuestions;
        this.mediumQuestions = mediumQuestions;
        this.hardQuestions = hardQuestions;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getEasyQuestions() {
        return this.easyQuestions;
    }

    public void setEasyQuestions(List<Question> easyQuestions) {
        this.easyQuestions = easyQuestions;
    }

    public List<Question> getMediumQuestions() {
        return this.mediumQuestions;
    }

    public void setMediumQuestions(List<Question> mediumQuestions) {
        this.mediumQuestions = mediumQuestions;
    }

    public List<Question> getHardQuestions() {
        return this.hardQuestions;
    }

    public void setHardQuestions(List<Question> hardQuestions) {
        this.hardQuestions = hardQuestions;
    }

    public CombinedQuestionResponse id(int id) {
        setId(id);
        return this;
    }

    public CombinedQuestionResponse easyQuestions(List<Question> easyQuestions) {
        setEasyQuestions(easyQuestions);
        return this;
    }

    public CombinedQuestionResponse mediumQuestions(List<Question> mediumQuestions) {
        setMediumQuestions(mediumQuestions);
        return this;
    }

    public CombinedQuestionResponse hardQuestions(List<Question> hardQuestions) {
        setHardQuestions(hardQuestions);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CombinedQuestionResponse)) {
            return false;
        }
        CombinedQuestionResponse combinedQuestionResponse = (CombinedQuestionResponse) o;
        return id == combinedQuestionResponse.id && Objects.equals(easyQuestions, combinedQuestionResponse.easyQuestions) && Objects.equals(mediumQuestions, combinedQuestionResponse.mediumQuestions) && Objects.equals(hardQuestions, combinedQuestionResponse.hardQuestions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, easyQuestions, mediumQuestions, hardQuestions);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", easyQuestions='" + getEasyQuestions() + "'" +
            ", mediumQuestions='" + getMediumQuestions() + "'" +
            ", hardQuestions='" + getHardQuestions() + "'" +
            "}";
    }
    
    
}
