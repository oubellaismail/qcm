

import model.Person;
import DAO.PersonDAO;
import DAOimplementation.PersonDAOimpl;

public class Main {
    public static void main(String[] args){
        PersonDAO personDAO = new PersonDAOimpl();
        Person person = new Person(1, "ismail", "null", "admin@gmail.com", "just");
        personDAO.insertPerson(person);
    }
}
