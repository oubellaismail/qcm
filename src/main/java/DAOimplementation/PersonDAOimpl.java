package DAOimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.PersonDAO;
import model.Person;


public class PersonDAOimpl implements PersonDAO {

    public String jdbcURL = "jdbc:mysql://localhost:3306/JavaQuiz";
    public String jdbcUsername = "ismail";
    public String jdbcPassword = "just";

    private static final String  INSERT_PERSON_SQL = "INSERT INTO Persons" +
        " (id, firstName, lastName, email, password) VALUES" + 
        "(?, ?, ?, ?, ?);"
    ;

    private static final String SELECT_PERSON_BY_ID_SQL = "SELECT * from Persons where id = ? ;";

    private static final String SELECT_PERSONS_SQL = "SELECT * from Persons";

    private static final String UPDATE_PERSON_SQL = "UPDATE Persons set firstName = ?, lastName = ?, email = ?, password =? WHERE id = ?;";

    private static final String DELETE_PERSON_SQL = "DELETE FROM Persons WHERE id = ?;";

    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

        catch (SQLException e){
            e.printStackTrace();
        }
        
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        
        return connection;
    }




    @Override
    public void insertPerson(Person person){
        try (Connection connection =getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSON_SQL);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Person findPerson(int id){
        Person person = null;
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSON_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String password = rs.getString("password");

                person = new Person(id, firstName, lastName, email, password);

            }
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }


    @Override
    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        try (Connection connection = getConnection();) {
        
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSONS_SQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName =  rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                
                persons.add(new Person(id, firstName, lastName, email, password));

            }
        } 
        
        catch (SQLException e) { 
            e.printStackTrace();
        }

        return persons;
    }


    @Override
    public void updatePerson(Person person){
        try (Connection connection = getConnection(); ) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON_SQL);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getEmail());
            statement.setString(4, person.getPassword());
            
            statement.setInt(5, person.getId());
            statement.executeUpdate();

        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deletePerson(int id){
        
        try (Connection connection = getConnection(); ) {
            PreparedStatement statement = connection.prepareStatement(DELETE_PERSON_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    


