package app;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import DAO.PersonDAO;
import DAOimplementation.PersonDAOimpl;
import model.Person;

public class main {
    public main() {
    }

    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAOimpl();
        Person person = new Person(2,"ismail", "null", "admin@gmail.com", "just");
        personDAO.insertPerson(person);
    }
}
