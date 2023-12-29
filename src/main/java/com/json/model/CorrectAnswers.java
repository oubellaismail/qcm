package com.json.model;
import java.util.Objects;

public class CorrectAnswers {
    private boolean answer_a_correct;
    private boolean answer_b_correct;
    private boolean answer_c_correct;
    private boolean answer_d_correct;
    private boolean answer_e_correct;

    public CorrectAnswers() {
    }

    public CorrectAnswers(boolean answer_a_correct, boolean answer_b_correct, boolean answer_c_correct, boolean answer_d_correct, boolean answer_e_correct, boolean answer_f_correct) {
        this.answer_a_correct = answer_a_correct;
        this.answer_b_correct = answer_b_correct;
        this.answer_c_correct = answer_c_correct;
        this.answer_d_correct = answer_d_correct;
        this.answer_e_correct = answer_e_correct;
        this.answer_f_correct = answer_f_correct;
    }

    public boolean isAnswer_a_correct() {
        return this.answer_a_correct;
    }

    public boolean getAnswer_a_correct() {
        return this.answer_a_correct;
    }

    public void setAnswer_a_correct(boolean answer_a_correct) {
        this.answer_a_correct = answer_a_correct;
    }

    public boolean isAnswer_b_correct() {
        return this.answer_b_correct;
    }

    public boolean getAnswer_b_correct() {
        return this.answer_b_correct;
    }

    public void setAnswer_b_correct(boolean answer_b_correct) {
        this.answer_b_correct = answer_b_correct;
    }

    public boolean isAnswer_c_correct() {
        return this.answer_c_correct;
    }

    public boolean getAnswer_c_correct() {
        return this.answer_c_correct;
    }

    public void setAnswer_c_correct(boolean answer_c_correct) {
        this.answer_c_correct = answer_c_correct;
    }

    public boolean isAnswer_d_correct() {
        return this.answer_d_correct;
    }

    public boolean getAnswer_d_correct() {
        return this.answer_d_correct;
    }

    public void setAnswer_d_correct(boolean answer_d_correct) {
        this.answer_d_correct = answer_d_correct;
    }

    public boolean isAnswer_e_correct() {
        return this.answer_e_correct;
    }

    public boolean getAnswer_e_correct() {
        return this.answer_e_correct;
    }

    public void setAnswer_e_correct(boolean answer_e_correct) {
        this.answer_e_correct = answer_e_correct;
    }

    public boolean isAnswer_f_correct() {
        return this.answer_f_correct;
    }

    public boolean getAnswer_f_correct() {
        return this.answer_f_correct;
    }

    public void setAnswer_f_correct(boolean answer_f_correct) {
        this.answer_f_correct = answer_f_correct;
    }

    public CorrectAnswers answer_a_correct(boolean answer_a_correct) {
        setAnswer_a_correct(answer_a_correct);
        return this;
    }

    public CorrectAnswers answer_b_correct(boolean answer_b_correct) {
        setAnswer_b_correct(answer_b_correct);
        return this;
    }

    public CorrectAnswers answer_c_correct(boolean answer_c_correct) {
        setAnswer_c_correct(answer_c_correct);
        return this;
    }

    public CorrectAnswers answer_d_correct(boolean answer_d_correct) {
        setAnswer_d_correct(answer_d_correct);
        return this;
    }

    public CorrectAnswers answer_e_correct(boolean answer_e_correct) {
        setAnswer_e_correct(answer_e_correct);
        return this;
    }

    public CorrectAnswers answer_f_correct(boolean answer_f_correct) {
        setAnswer_f_correct(answer_f_correct);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CorrectAnswers)) {
            return false;
        }
        CorrectAnswers correctAnswers = (CorrectAnswers) o;
        return answer_a_correct == correctAnswers.answer_a_correct && answer_b_correct == correctAnswers.answer_b_correct && answer_c_correct == correctAnswers.answer_c_correct && answer_d_correct == correctAnswers.answer_d_correct && answer_e_correct == correctAnswers.answer_e_correct && answer_f_correct == correctAnswers.answer_f_correct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer_a_correct, answer_b_correct, answer_c_correct, answer_d_correct, answer_e_correct, answer_f_correct);
    }

    @Override
    public String toString() {
        return "{" +
            " answer_a_correct='" + isAnswer_a_correct() + "'" +
            ", answer_b_correct='" + isAnswer_b_correct() + "'" +
            ", answer_c_correct='" + isAnswer_c_correct() + "'" +
            ", answer_d_correct='" + isAnswer_d_correct() + "'" +
            ", answer_e_correct='" + isAnswer_e_correct() + "'" +
            ", answer_f_correct='" + isAnswer_f_correct() + "'" +
            "}";
    }
    private boolean answer_f_correct;
}
