package DAO;

import model.Professor;
import model.Quiz;

import java.util.List;

public interface ProfessorDAO extends QuizDAO{
    void insertProfessor(Professor professor);
    Professor findProfessor(int id);
    List<Professor> findAllProf();
    void updateProfessor(Professor professor);
    void deleteProfessor(int id);
    public void insertQuiz(int id, Quiz quiz);
    public Quiz findQuiz(int id);
    public List<Quiz> findAllQuizs();
    public void updateQuiz(Quiz quiz);
    public void deleteQuiz(int id);
}
