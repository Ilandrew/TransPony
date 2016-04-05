package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.User;

import java.util.List;

public interface IUser {

    List<User> getAll();
    List<User> getFreeDriver();

    void delete(User user);
    void update(User user);
    void add(User user);
}
