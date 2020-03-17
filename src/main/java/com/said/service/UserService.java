package com.said.service;

import com.said.model.User;

import java.util.List;

public interface UserService {
     User getUserById(long id);
     List<User> getAllUsers();
     void addUser(User user);
     void updateUser(User user);
     void deleteUser(User user);
     boolean userIsExist(String login, String password);
     User getUserByLoginAndPassword(String login, String password);
}
