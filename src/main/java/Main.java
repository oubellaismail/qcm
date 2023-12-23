import model.Person;
import model.Professor;

import java.util.List;

import DAO.PersonDAO;
import DAOimplementation.PersonDAOimpl;
import DAOimplementation.ProfessorDAOimpl;

public class Main {
    public static void main(String[] args) {
        PersonDAO personDAO = new ProfessorDAOimpl();
    }
}
