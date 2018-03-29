package com.work.backendlibrary.service;

import com.work.backendlibrary.entity.User;

import java.util.List;

public interface UserService {

    User login(String user);

    User addUser(User user);

    List<User> listAllUsers();

    void findById(int id);

    void deleteUserById(int id);

}
