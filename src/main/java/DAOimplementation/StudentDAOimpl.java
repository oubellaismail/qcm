package DAOimplementation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.StudentDAO;
import model.Student;

public class StudentDAOimpl implements StudentDAO {
    public String jdbcURL = "jdbc:mysql://localhost:3306/JavaQuiz";
    public String jdbcUsername = "ismail";
    public String jdbcPassword = "just";

    private static final String INSERT_PERSON_SQL = "INSERT INTO Persons " + 
            "(firstName, lastName, email, password) VALUES" +
            "(?, ?, ?, ?)";

    private static final String SELECT_PERSON_BY_ID_SQL = "SELECT * from Persons where id = ? ;";

            
    private static final String UPDATE_PERSON_SQL = "UPDATE Persons set firstName = ?, lastName = ?, email = ?, password =? WHERE id = ?;";

    private static final String DELETE_PERSON_SQL = "DELETE FROM Persons WHERE id = ?;";

    private static final String INSERT_STUDENT_SQL = "INSERT INTO Students " +
        "(personId) VALUES" +
        "(?)";

    private static final String SELECT_STUDENT_BY_ID_SQL = "SELECT * from Students where id = ? ;";
    private static final String SELECT_STUDENTS_SQL = "SELECT * from Students";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM Students WHERE id = ? ;";


    protected Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    public void insertStudent(Student student) {
        try (Connection connection = getConnection()) {
            PreparedStatement personStatement = connection.prepareStatement(INSERT_PERSON_SQL,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            personStatement.setString(1, student.getFirstName());
            personStatement.setString(2, student.getLastName());
            personStatement.setString(3, student.getEmail());
            personStatement.setString(4, student.getPassword());

            int affectedRows = personStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }

            try (ResultSet generatedKeys = personStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int personId = generatedKeys.getInt(1); 

                    try (PreparedStatement studentStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
                        studentStatement.setInt(1, personId);
                        studentStatement.executeUpdate();
                    }
                } else {
                    throw new SQLException("Creating person failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findStudent(int id){
        Student student = null;
        try (Connection connection = getConnection();) {
            PreparedStatement studentStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID_SQL);
            PreparedStatement personStatement = connection.prepareStatement(SELECT_PERSON_BY_ID_SQL);
            
            studentStatement.setInt(1,id);
            
            try (ResultSet rs = studentStatement.executeQuery();) {

                while (rs.next()) {
                    int idPerson = rs.getInt("personId");
                    personStatement.setInt(1, idPerson);

                    try (ResultSet resultSet = personStatement.executeQuery();) {

                        while (resultSet.next()) {
                            int id_person = resultSet.getInt("id");
                            String firstName = resultSet.getString("firstName");
                            String lastName = resultSet.getString("lastName");
                            String email = resultSet.getString("email");
                            String password = resultSet.getString("password");
        
                            student = new Student(id_person, firstName, lastName, email, password);
                            student.setIdStudent(id);
                        }
                    }
                }
            }


        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public List<Student> findAll(){
        List <Student> students = new  ArrayList<>();
        try (Connection connection = getConnection() ;) {
            PreparedStatement studentStatement = connection.prepareStatement(SELECT_STUDENTS_SQL);
            PreparedStatement personStatement = connection.prepareStatement(SELECT_PERSON_BY_ID_SQL);

            try (ResultSet profRs = studentStatement.executeQuery()){
                while (profRs.next()) {
                    int stdID = profRs.getInt("id");
                    int personId = profRs.getInt("personId");
                    personStatement.setInt(1, personId);

                    try (ResultSet perRs = personStatement.executeQuery()) {
                        while (perRs.next()) {
                            int id = perRs.getInt("id");
                            String firstName = perRs.getString("firstName");
                            String lastName = perRs.getString("lastName");
                            String email = perRs.getString("email");
                            String password = perRs.getString("password");
                            Student student = new Student(id, firstName, lastName, email, password);
                            student.setIdStudent(stdID);
                            students.add(student);

                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return students;
    }



    @Override
    public void updateStudent(Student student){
        try (Connection connection = getConnection(); ) {
            PreparedStatement personStatement = connection.prepareStatement(UPDATE_PERSON_SQL);

            personStatement.setString(1, student.getFirstName());
            personStatement.setString(2, student.getLastName());
            personStatement.setString(3, student.getEmail());
            personStatement.setString(4, student.getPassword());  
            personStatement.setInt(5, student.getId());
            
            personStatement.executeUpdate();

        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id){
        try (Connection connection = getConnection();) {
            PreparedStatement personStatement = connection.prepareStatement(DELETE_PERSON_SQL);
            PreparedStatement std_Delete_Statement = connection.prepareStatement(DELETE_STUDENT_SQL);
            PreparedStatement std_Select_Statement = connection.prepareStatement(SELECT_STUDENT_BY_ID_SQL);

            std_Select_Statement.setInt(1, id);
            try (ResultSet selectRs = std_Select_Statement.executeQuery()){
                if(selectRs.next()){
                    int personId = selectRs.getInt("personId");

                    std_Delete_Statement.setInt(1, id);
                    std_Delete_Statement.executeUpdate();

                    personStatement.setInt(1, personId);
                    personStatement.executeUpdate();
                }

                else {
                    System.err.println("No person found with id " + id);
                }

            }

        } 

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
