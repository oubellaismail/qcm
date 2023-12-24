package DAOimplementation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ProfessorDAO;
import model.Professor;

public class ProfessorDAOimpl implements ProfessorDAO {
    public String jdbcURL = "jdbc:mysql://localhost:3306/JavaQuiz";
    public String jdbcUsername = "ismail";
    public String jdbcPassword = "just";

    private static final String INSERT_PERSON_SQL = "INSERT INTO Persons " + 
            "(firstName, lastName, email, password) VALUES" +
            "(?, ?, ?, ?)";

    private static final String SELECT_PERSON_BY_ID_SQL = "SELECT * from Persons where id = ? ;";

            
    private static final String UPDATE_PERSON_SQL = "UPDATE Persons set firstName = ?, lastName = ?, email = ?, password =? WHERE id = ?;";

    private static final String DELETE_PERSON_SQL = "DELETE FROM Persons WHERE id = ?;";

    private static final String INSERT_PROFESSOR_SQL = "INSERT INTO Professors " +
        "(personId) VALUES" +
        "(?)";

    private static final String SELECT_PROFESSOR_BY_ID_SQL = "SELECT * from Professors where id = ? ;";
    private static final String SELECT_PROFESSORS_SQL = "SELECT * from Professors";
    private static final String DELETE_PROFESSOR_SQL = "DELETE FROM Professors WHERE id = ? ;";


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
    public void insertProfessor(Professor professor) {
        try (Connection connection = getConnection()) {
            PreparedStatement personStatement = connection.prepareStatement(INSERT_PERSON_SQL,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            personStatement.setString(1, professor.getFirstName());
            personStatement.setString(2, professor.getLastName());
            personStatement.setString(3, professor.getEmail());
            personStatement.setString(4, professor.getPassword());

            int affectedRows = personStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }

            try (ResultSet generatedKeys = personStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int personId = generatedKeys.getInt(1); 

                    try (PreparedStatement professorStatement = connection.prepareStatement(INSERT_PROFESSOR_SQL)) {
                        professorStatement.setInt(1, personId);
                        professorStatement.executeUpdate();
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
    public Professor findProfessor(int id){
        Professor professor = null;
        try (Connection connection = getConnection();) {
            PreparedStatement profStatement = connection.prepareStatement(SELECT_PROFESSOR_BY_ID_SQL);
            PreparedStatement personStatement = connection.prepareStatement(SELECT_PERSON_BY_ID_SQL);
            
            profStatement.setInt(1,id);
            
            try (ResultSet rs = profStatement.executeQuery();) {

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
        
                            professor = new Professor(id_person, firstName, lastName, email, password);
                            professor.setIdProfessor(id);
                        }
                    }
                }
            }


        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }

        return professor;
    }

    @Override
    public List<Professor> findAll(){
        List <Professor> professors = new  ArrayList<>();
        try (Connection connection = getConnection() ;) {
            PreparedStatement profStatement = connection.prepareStatement(SELECT_PROFESSORS_SQL);
            PreparedStatement personStatement = connection.prepareStatement(SELECT_PERSON_BY_ID_SQL);

            try (ResultSet profRs = profStatement.executeQuery()){
                while (profRs.next()) {
                    int profID = profRs.getInt("id");
                    int personId = profRs.getInt("personId");
                    personStatement.setInt(1, personId);

                    try (ResultSet perRs = personStatement.executeQuery()) {
                        while (perRs.next()) {
                            int id = perRs.getInt("id");
                            String firstName = perRs.getString("firstName");
                            String lastName = perRs.getString("lastName");
                            String email = perRs.getString("email");
                            String password = perRs.getString("password");
                            Professor professor = new Professor(id, firstName, lastName, email, password);
                            professor.setIdProfessor(profID);
                            professors.add(professor);

                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return professors;
    }



    @Override
    public void updateProfessor(Professor professor){
        try (Connection connection = getConnection(); ) {
            PreparedStatement personStatement = connection.prepareStatement(UPDATE_PERSON_SQL);

            personStatement.setString(1, professor.getFirstName());
            personStatement.setString(2, professor.getLastName());
            personStatement.setString(3, professor.getEmail());
            personStatement.setString(4, professor.getPassword());  
            personStatement.setInt(5, professor.getId());
            
            personStatement.executeUpdate();

        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProfessor(int id){
        try (Connection connection = getConnection();) {
            PreparedStatement personStatement = connection.prepareStatement(DELETE_PERSON_SQL);
            PreparedStatement prof_Delete_Statement = connection.prepareStatement(DELETE_PROFESSOR_SQL);
            PreparedStatement prof_Select_Statement = connection.prepareStatement(SELECT_PROFESSOR_BY_ID_SQL);

            prof_Select_Statement.setInt(1, id);
            try (ResultSet selectRs = prof_Select_Statement.executeQuery()){
                if(selectRs.next()){
                    int personId = selectRs.getInt("personId");

                    prof_Delete_Statement.setInt(1, id);
                    prof_Delete_Statement.executeUpdate();

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
