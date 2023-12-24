package app;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import DAO.ProfessorDAO;
import DAOimplementation.ProfessorDAOimpl;
import model.Person;
import model.Professor;

public class Main {
    public static void main(String[] args) {
        ProfessorDAO professorDAO = new ProfessorDAOimpl();
        // professorDAO.insertPerson(new Professor(0, "johan", "laddies", "and", "pass"));
        // professorDAO.deletePerson(6);

        Professor professor =  professorDAO.findProfessor(7);

        System.out.println(professor.getIdProfessor() + " " + professor.getId() + " " + professor.getFirstName() + " " + professor.getLastName() + " " + professor.getEmail() + " " +  professor.getPassword());
        // professor.setPassword("null");
        // System.out.println(professor.getIdProfessor() + " " + professor.getId() + " " + professor.getFirstName() + " " + professor.getLastName() + " " + professor.getEmail() + " " +  professor.getPassword());
        // professorDAO.updateProfessor(professor);

        // Professor pro = professorDAO.findProfessor(7);
        // System.out.println(pro.getIdProfessor() + " " + pro.getId() + " " + pro.getFirstName() + " " + pro.getLastName() + " " + pro.getEmail() + " " +  pro.getPassword());

        professorDAO.deleteProfessor(7);
    }
}