package model;

public class Mark {
    private int studentId;
    private int quizId;
    private int score;

    public Mark(int studentId, int quizId, int score) {
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
