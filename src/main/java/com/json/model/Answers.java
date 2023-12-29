package com.json.model;
import java.util.Objects;
public class Answers {
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String answer_e;
    private String answer_f;



    public Answers() {
    }

    public Answers(String answer_a, String answer_b, String answer_c, String answer_d, String answer_e, String answer_f) {
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
        this.answer_e = answer_e;
        this.answer_f = answer_f;
    }

    public String getAnswer_a() {
        return this.answer_a;
    }

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public String getAnswer_b() {
        return this.answer_b;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public String getAnswer_c() {
        return this.answer_c;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public String getAnswer_d() {
        return this.answer_d;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public String getAnswer_e() {
        return this.answer_e;
    }

    public void setAnswer_e(String answer_e) {
        this.answer_e = answer_e;
    }

    public String getAnswer_f() {
        return this.answer_f;
    }

    public void setAnswer_f(String answer_f) {
        this.answer_f = answer_f;
    }

    public Answers answer_a(String answer_a) {
        setAnswer_a(answer_a);
        return this;
    }

    public Answers answer_b(String answer_b) {
        setAnswer_b(answer_b);
        return this;
    }

    public Answers answer_c(String answer_c) {
        setAnswer_c(answer_c);
        return this;
    }

    public Answers answer_d(String answer_d) {
        setAnswer_d(answer_d);
        return this;
    }

    public Answers answer_e(String answer_e) {
        setAnswer_e(answer_e);
        return this;
    }

    public Answers answer_f(String answer_f) {
        setAnswer_f(answer_f);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Answers)) {
            return false;
        }
        Answers answers = (Answers) o;
        return Objects.equals(answer_a, answers.answer_a) && Objects.equals(answer_b, answers.answer_b) && Objects.equals(answer_c, answers.answer_c) && Objects.equals(answer_d, answers.answer_d) && Objects.equals(answer_e, answers.answer_e) && Objects.equals(answer_f, answers.answer_f);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer_a, answer_b, answer_c, answer_d, answer_e, answer_f);
    }

    @Override
    public String toString() {
        return "{" +
            " answer_a='" + getAnswer_a() + "'" +
            ", answer_b='" + getAnswer_b() + "'" +
            ", answer_c='" + getAnswer_c() + "'" +
            ", answer_d='" + getAnswer_d() + "'" +
            ", answer_e='" + getAnswer_e() + "'" +
            ", answer_f='" + getAnswer_f() + "'" +
            "}";
    }


} 
