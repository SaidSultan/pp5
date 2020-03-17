package com.said.service;

import com.said.dao.UserDAO;
import com.said.factory.UserDaoFactory;
import com.said.model.User;

import java.util.List;

public class UserServiceImpl implements  UserService{
    private static UserServiceImpl userServiceImpl;
    private UserDAO userDAO;

    private UserServiceImpl() {
        userDAO = UserDaoFactory.createDAO();
    }
    public static UserServiceImpl getInstance() {
        if(userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUSer(user.getId());
    }

    @Override
    public boolean userIsExist(String login, String password) {
        return userDAO.userIsExist(login, password);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password){
        return userDAO.getUserByLoginAndPassword(login, password);
    }
}
