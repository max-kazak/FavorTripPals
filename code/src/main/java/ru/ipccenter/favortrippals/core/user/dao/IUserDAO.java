package ru.ipccenter.favortrippals.core.user.dao;

import java.util.List;

import ru.ipccenter.favortrippals.core.model.User;


public interface IUserDAO {


public void addUser(User user);


public void updateUser(User user);


public void deleteUser(User user);


public User getUserById(long id);

public User getUserByLogin(String login);

public List<User> getUsers();
}
