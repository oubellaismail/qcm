package DAO;

import model.Person;
import java.util.List;

public interface PersonDAO {
    void insertPerson(Person person);
    Person findPerson(int id);
    List<Person> findAll();
    void updatePerson(Person person);
    void deletePerson(int id);
}
