// package DAOimplementation;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import DAO.PersonDAO;
// import DAO.QuizDAO;
// import model.Person;



// public class QuizDAOimp implements QuizDAO {

//     public String jdbcURL = "jdbc:mysql://localhost:3306/JavaQuiz";
//     public String jdbcUsername = "ismail";
//     public String jdbcPassword = "just";

//     private static final String  INSERT_QUIZ_SQL = "INSERT INTO Quizs" +
//         " (title, description, professorId) VALUES" + 
//         "(?, ?, ?);"
//     ;

//     private static final String SELECT_QUIZ_BY_ID_SQL = "SELECT * from Quizs where id = ? ;";

//     private static final String SELECT_QUIZS_SQL = "SELECT * from Quizs";

//     private static final String UPDATE_QUIZ_SQL = "UPDATE Quizs set title = ?, description = ? WHERE id = ?;";

//     private static final String DELETE_QUIZ_SQL = "DELETE FROM Quizs WHERE id = ?;";

//     protected Connection getConnection(){
//         Connection connection = null;
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//             connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//         }

//         catch (SQLException e){
//             e.printStackTrace();
//         }
        
//         catch(ClassNotFoundException e){
//             e.printStackTrace();
//         }
        
//         return connection;
//     }




//     @Override
//     public void insertQuiz(Quiz quiz){
//         try (Connection connection =getConnection();){
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUIZ_SQL);
//             preparedStatement.setInt(1, person.getId());
//             preparedStatement.setString(2, person.getFirstName());
//             preparedStatement.setString(3, person.getLastName());
//             preparedStatement.setString(4, person.getEmail());
//             preparedStatement.setString(5, person.getPassword());

//             preparedStatement.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }



//     @Override
//     public Person findQuiz(int id){
//         Person person = null;
//         try (Connection connection = getConnection();) {
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUIZ_BY_ID_SQL);
//             preparedStatement.setInt(1, id);
//             ResultSet rs = preparedStatement.executeQuery();

//             while (rs.next()) {
//                 String firstName = rs.getString("firstName");
//                 String lastName = rs.getString("lastName");
//                 String email = rs.getString("email");
//                 String password = rs.getString("password");

//                 person = new Person(id, firstName, lastName, email, password);

//             }
//         } 
        
//         catch (SQLException e) {
//             e.printStackTrace();
//         }

//         return person;
//     }


//     @Override
//     public List<Quiz> findAll(){
//         List<Person> persons = new ArrayList<>();
//         try (Connection connection = getConnection();) {
        
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUIZS_SQL);
//             ResultSet rs = preparedStatement.executeQuery();

//             while (rs.next()) {
//                 int id = rs.getInt("id");
//                 String firstName =  rs.getString("firstName");
//                 String lastName = rs.getString("lastName");
//                 String email = rs.getString("email");
//                 String password = rs.getString("password");
                
//                 persons.add(new Person(id, firstName, lastName, email, password));

//             }
//         } 
        
//         catch (SQLException e) { 
//             e.printStackTrace();
//         }

//         return persons;
//     }


//     @Override
//     public void updateQuiz(Quiz quiz){
//         try (Connection connection = getConnection(); ) {
//             PreparedStatement statement = connection.prepareStatement(UPDATE_QUIZ_SQL);
//             statement.setString(1, person.getFirstName());
//             statement.setString(2, person.getLastName());
//             statement.setString(3, person.getEmail());
//             statement.setString(4, person.getPassword());
            
//             statement.setInt(5, person.getId());
//             statement.executeUpdate();

//         } 
        
//         catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }


//     @Override
//     public void deleteQuiz(int id){
        
//         try (Connection connection = getConnection(); ) {
//             PreparedStatement statement = connection.prepareStatement(DELETE_QUIZ_SQL);
//             statement.setInt(1, id);
//             statement.executeUpdate();
//         } 
        
//         catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }
    


