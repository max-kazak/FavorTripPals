package ru.ipccenter.favortrippals.core.user.service;

import java.util.List;

import ru.ipccenter.favortrippals.core.model.User;


public interface IUserService
{
    public User getCurrentUser ();
    public void setCurrentUser (User user); 
    public void addUser (User user);
    public void updateUser (User user);
    public void deleteUser (User user);
    public User getUserById (long id);
    public User getUserByEmail (String login);
    /**
     * See ISocialConnectionDAO.getConnectionByProviderUserId
     */
    public User getUserByProviderUserId(String provider, String providerUserId);
    public List<User> getUsers ();
    public String getUrlOfSmallPicture(long id);
}
