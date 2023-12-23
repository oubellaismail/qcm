package DAO;
import model.Person;
import java.util.*;
public interface PersonDAO {
    public void insertPerson(Person person);
    public Person findPerson(int id);
    public List<Person> findAll();
    public void updatePerson(Person person);
    public void deletePerson(int id);
}
