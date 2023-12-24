package DAO;

import model.Professor;
import java.util.List;

public interface ProfessorDAO {
    void insertProfessor(Professor professor);
    Professor findProfessor(int id);
    List<Professor> findAll();
    void updateProfessor(Professor professor);
    void deleteProfessor(int id);
}
