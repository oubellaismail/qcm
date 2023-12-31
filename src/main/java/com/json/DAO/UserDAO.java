package com.json.DAO;
import com.json.model.User;


public interface UserDAO {
    void inserUser(User user);
    User findUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
