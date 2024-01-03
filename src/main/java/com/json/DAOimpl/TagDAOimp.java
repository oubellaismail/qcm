package com.json.DAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.json.DAO.TagDAO;
import com.json.model.Tag;

public class TagDAOimp implements TagDAO {
    public String jdbcUrl = "jdbc:mysql://localhost:3306/quiz-app";

//    public String jdbcUser = "ismail";
//    public String jdbcPassword = "just";

     public String jdbcUser = "root";
     public String jdbcPassword = "";

    private static final String INSERT_TAGS_SQL = "INSERT INTO TAGS " +
            "(name) VALUES " +
            "(?) ;";

    private static final String SELECT_TAGS_BY_ID = "SELECT * FROM TAGS WHERE id = ? ;";

    private static final String SELECT_TAGS_BY_NAME = "SELECT * FROM TAGS WHERE name = ?;";

    private static final String DELETE_TAGS_SQL = "DELETE FROM TAGS WHERE id = ? ;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public int insertTag(Tag tag) {
        try (Connection connection = getConnection()) {
            
            Tag existingTag = findTAgbyName(tag.getName());
            
            if ( existingTag != null) {
                return existingTag.getId();
            } 
            
            else {
                PreparedStatement tagStatement = connection.prepareStatement(INSERT_TAGS_SQL,
                        PreparedStatement.RETURN_GENERATED_KEYS);
    
                tagStatement.setString(1, tag.getName());
    
                tagStatement.executeUpdate();
    
                try (ResultSet generatedKeys = tagStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating Answers failed, no ID obtained.");
    
                    }
                }
            }
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Tag findTAgbyName(String name) {
        Tag tag = null;
        try (Connection connection = getConnection()) {
            PreparedStatement tagStatement = connection.prepareStatement(SELECT_TAGS_BY_NAME);
            tagStatement.setString(1, name);

            try (ResultSet resultSet = tagStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    tag = new Tag(id, name);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tag;
    }

    public Tag findTag(int id) {
        Tag tag = null;
        try (Connection connection = getConnection()) {
            PreparedStatement tagStatement = connection.prepareStatement(SELECT_TAGS_BY_ID);
            tagStatement.setInt(1, id);

            try (ResultSet resultSet = tagStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");

                    tag = new Tag(id, name);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tag;
    }

    public void deleteTag(int id) {
        try (Connection connection = getConnection();) {
            PreparedStatement tagStatement = connection.prepareStatement(DELETE_TAGS_SQL);

            tagStatement.setInt(1, id);
            tagStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}