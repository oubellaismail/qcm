package model;

public class Student extends Person{
    private int idStudent;
    
    public Student(int idStudent, String firstName, String lastName, String email, String password) {
        super(idStudent, firstName, lastName, email, password);
    }
    
    public int getIdStudent() {
        return this.idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
}
