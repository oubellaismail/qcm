package DAO;

import model.Student;
import java.util.List;

public interface StudentDAO {
    void insertStudent(Student student);
    Student findStudent(int id);
    List<Student> findAll();
    void updateStudent(Student student);
    void deleteStudent(int id);
}
