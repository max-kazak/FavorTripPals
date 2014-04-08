package ru.ipccenter.favortrippals.core.user.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;
import ru.ipccenter.favortrippals.core.model.SocialConnection;

import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.socialconnection.dao.ISocialConnectionDAO;
import ru.ipccenter.favortrippals.core.user.dao.IUserDAO;



@Transactional(readOnly = true)
public class UserService implements IUserService
{
    User currentUser;
    IUserDAO userDAO;
    ISocialConnectionDAO socialConnectionDAO;

    public ISocialConnectionDAO getSocialConnectionDAO()
    {
        return socialConnectionDAO;
    }

    public void setSocialConnectionDAO(ISocialConnectionDAO socialConnectionDAO)
    {
        this.socialConnectionDAO = socialConnectionDAO;
    }
    
    public IUserDAO getUserDAO ()
    {
        return userDAO;
    }

    public void setUserDAO (IUserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @Override
    public User getCurrentUser ()
    {
        return currentUser;
    }
    
    @Override
    public void setCurrentUser (User currentUser)
    {
        this.currentUser = currentUser;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addUser(User user)
    {
        getUserDAO().addUser(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteUser(User user)
    {
        getUserDAO().deleteUser(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateUser(User user)
    {
        getUserDAO().updateUser(user);
    }
    
    @Override
    public User getUserById(long id)
    {
        currentUser = getUserDAO().getUserById(id);
        return currentUser;
    }
    
    @Override
    public User getUserByEmail(String email)
    {
        currentUser = getUserDAO().getUserByEmail(email);
        return currentUser;
    }

    @Override
    public List<User> getUsers()
    {
        return getUserDAO().getUsers();
    }
    
    /**
     * See ISocialConnectionDAO.getConnectionByProviderUserId
     */
    @Override
    public User getUserByProviderUserId(String provider, String providerUserId)
    {
        SocialConnection sConnection = getSocialConnectionDAO().getConnectionByProviderUserId(provider, providerUserId);
        if (sConnection == null)
            return null;
        User user = getUserDAO().getUserById(sConnection.getUser().getId());
        return user;
    }
}
