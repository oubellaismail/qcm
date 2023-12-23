package model;

public class Professor extends Person{
    private int idProfessor;

    public Professor(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }
    
    public int getIdProfessor() {
        return this.idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
}
