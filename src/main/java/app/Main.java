package app;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.List;

import DAO.StudentDAO;
import DAOimplementation.ProfessorDAOimpl;
import DAOimplementation.StudentDAOimpl;
import model.Professor;
import model.Quiz;
import model.Student;
import DAO.ProfessorDAO;
import DAO.QuizDAO;

public class Main {
    public static void main(String[] args) {


        // //! Student Dao

        // StudentDAO studentDAO = new StudentDAOimpl();
        
        // //insert Student
        // studentDAO.insertStudent(new Student(0, "johan", "youtube", "he", "lqjf"));
        // studentDAO.insertStudent(new Student(0, "name", "lad", "qf", "lkqjf"));
        // studentDAO.insertStudent(new Student(0, "pass", "ladddfqies", "qf", "qljf"));
        // studentDAO.deleteStudent(1);
        
        // //find Student
        // Student student =  studentDAO.findStudent(4);
        // System.out.println(student.getIdStudent() + " " + student.getId() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getEmail() + " " +  student.getPassword());

        // //update student
        // student.setFirstName("null");
        // student.setLastName("null");
        // student.setEmail("null");
        // student.setPassword("null");
        
        // System.out.println(student.getIdStudent() + " " + student.getId() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getEmail() + " " +  student.getPassword());
        // studentDAO.updateStudent(student);
        
        // Student pro = studentDAO.findStudent(4);
        // System.out.println(pro.getIdStudent() + " " + pro.getId() + " " + pro.getFirstName() + " " + pro.getLastName() + " " + pro.getEmail() + " " +  pro.getPassword());


        // //delete Student
        // studentDAO.deleteStudent(4);



        // List<Student> students = studentDAO.findAll();
        // for(Student item : students){
        //     System.out.println(item.getIdStudent() + " " + item.getId() + " " + item.getFirstName() + " " + item.getLastName() + " " + item.getEmail() + " " +  item.getPassword());

        // }

        //! Professor DAO

        ProfessorDAO ProfessorDAO = new ProfessorDAOimpl();

        // ProfessorDAO.insertProfessor(new Professor(0, "johan", "youtube", "he", "lqjf"));
        // ProfessorDAO.insertProfessor(new Professor(0, "name", "lad", "qf", "lkqjf"));
        // ProfessorDAO.insertProfessor(new Professor(0, "pass", "ladddfqies", "qf", "qljf"));
        
        // studentDAO.deleteStudent(1);
        
        // Student student =  studentDAO.findStudent(4);

        // System.out.println(student.getIdStudent() + " " + student.getId() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getEmail() + " " +  student.getPassword());

        // student.setFirstName("null");
        // student.setLastName("null");
        // student.setEmail("null");
        // student.setPassword("null");
        
        // System.out.println(student.getIdStudent() + " " + student.getId() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getEmail() + " " +  student.getPassword());
        // studentDAO.updateStudent(student);
        
        // Student pro = studentDAO.findStudent(4);
        // System.out.println(pro.getIdStudent() + " " + pro.getId() + " " + pro.getFirstName() + " " + pro.getLastName() + " " + pro.getEmail() + " " +  pro.getPassword());

        // studentDAO.deleteStudent(4);



        // List<Professor> professors = ProfessorDAO.findAllProf();
        // for(Professor professor : professors){
        //     System.out.println(professor.getIdProfessor() + " " + professor.getId() + " " + professor.getFirstName() + " " + professor.getLastName() + " " + professor.getEmail() + " " +  professor.getPassword());
        
        //     ProfessorDAO.insertQuiz(professor.getIdProfessor(), new Quiz(0, "null", "null", 0));
        // }
        
        


        //! Quiz DAO 
        
            //? find Quiz

        // Quiz quiz = ProfessorDAO.findQuiz(9);
        // System.out.println(quiz.getId() + " " + quiz.getTitle() + " " + quiz.getDescription() + " " + quiz.getProfessorId());
        

            //? update Quiz

        // quiz.setTitle("new");
        // quiz.setDescription("year");
        
        // ProfessorDAO.updateQuiz(quiz);
        // System.out.println(quiz.getId() + " " + quiz.getTitle() + " " + quiz.getDescription() + " " + quiz.getProfessorId());


            //? find all Quizs
        // List<Quiz> quizs = ProfessorDAO.findAllQuizs();
        // for(Quiz quiz : quizs){
        //     System.out.println(quiz.getId() + " " + quiz.getTitle() + " " + quiz.getDescription() + " " + quiz.getProfessorId());
        // }
    
            //? delete Quiz

        ProfessorDAO.deleteQuiz(5);
    
    }

}