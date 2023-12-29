package com.json.model;
import java.util.List;
import java.util.Objects;

public class Question {
    private int id;
    private String question;
    private String description;
    private Answers answers;
    private boolean multiple_correct_answers;
    private CorrectAnswers correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;
    private List<Tag> tags;
    private String category;
    private String difficulty;


    public Question() {
    }

    public Question(int id, String question, String description, Answers answers, boolean multiple_correct_answers, CorrectAnswers correct_answers, String correct_answer, String explanation, String tip, List<Tag> tags, String category, String difficulty) {
        this.id = id;
        this.question = question;
        this.description = description;
        this.answers = answers;
        this.multiple_correct_answers = multiple_correct_answers;
        this.correct_answers = correct_answers;
        this.correct_answer = correct_answer;
        this.explanation = explanation;
        this.tip = tip;
        this.tags = tags;
        this.category = category;
        this.difficulty = difficulty;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Answers getAnswers() {
        return this.answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public boolean isMultiple_correct_answers() {
        return this.multiple_correct_answers;
    }

    public boolean getMultiple_correct_answers() {
        return this.multiple_correct_answers;
    }

    public void setMultiple_correct_answers(boolean multiple_correct_answers) {
        this.multiple_correct_answers = multiple_correct_answers;
    }

    public CorrectAnswers getCorrect_answers() {
        return this.correct_answers;
    }

    public void setCorrect_answers(CorrectAnswers correct_answers) {
        this.correct_answers = correct_answers;
    }

    public String getCorrect_answer() {
        return this.correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Question id(int id) {
        setId(id);
        return this;
    }

    public Question question(String question) {
        setQuestion(question);
        return this;
    }

    public Question description(String description) {
        setDescription(description);
        return this;
    }

    public Question answers(Answers answers) {
        setAnswers(answers);
        return this;
    }

    public Question multiple_correct_answers(boolean multiple_correct_answers) {
        setMultiple_correct_answers(multiple_correct_answers);
        return this;
    }

    public Question correct_answers(CorrectAnswers correct_answers) {
        setCorrect_answers(correct_answers);
        return this;
    }

    public Question correct_answer(String correct_answer) {
        setCorrect_answer(correct_answer);
        return this;
    }

    public Question explanation(String explanation) {
        setExplanation(explanation);
        return this;
    }

    public Question tip(String tip) {
        setTip(tip);
        return this;
    }

    public Question tags(List<Tag> tags) {
        setTags(tags);
        return this;
    }

    public Question category(String category) {
        setCategory(category);
        return this;
    }

    public Question difficulty(String difficulty) {
        setDifficulty(difficulty);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return id == question.id && Objects.equals(question, question.question) && Objects.equals(description, question.description) && Objects.equals(answers, question.answers) && multiple_correct_answers == question.multiple_correct_answers && Objects.equals(correct_answers, question.correct_answers) && Objects.equals(correct_answer, question.correct_answer) && Objects.equals(explanation, question.explanation) && Objects.equals(tip, question.tip) && Objects.equals(tags, question.tags) && Objects.equals(category, question.category) && Objects.equals(difficulty, question.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, description, answers, multiple_correct_answers, correct_answers, correct_answer, explanation, tip, tags, category, difficulty);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", question='" + getQuestion() + "'" +
            ", description='" + getDescription() + "'" +
            ", answers='" + getAnswers() + "'" +
            ", multiple_correct_answers='" + isMultiple_correct_answers() + "'" +
            ", correct_answers='" + getCorrect_answers() + "'" +
            ", correct_answer='" + getCorrect_answer() + "'" +
            ", explanation='" + getExplanation() + "'" +
            ", tip='" + getTip() + "'" +
            ", tags='" + getTags() + "'" +
            ", category='" + getCategory() + "'" +
            ", difficulty='" + getDifficulty() + "'" +
            "}";
    }
    
}

    
