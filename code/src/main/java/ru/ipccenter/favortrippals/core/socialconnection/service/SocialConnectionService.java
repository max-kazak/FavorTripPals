package ru.ipccenter.favortrippals.core.socialconnection.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.transaction.annotation.Transactional;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.socialconnection.dao.ISocialConnectionDAO;

/**
 * @author Ddiimmaann
 */
@Transactional(readOnly = true)
public class SocialConnectionService implements ISocialConnectionService
{
    Connection currentConnection;
    SocialConnection currentSocialConnection;
    ISocialConnectionDAO socialConnectionDAO;
    UsersConnectionRepository usersConnectionRepository;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository()
    {
        return usersConnectionRepository;
    }

    @Override
    public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository)
    {
        this.usersConnectionRepository = usersConnectionRepository;
    }
    
    @Override
    public Connection getCurrentConnection()
    {
        return currentConnection;
    }

    @Override
    public void setCurrentConnection(Connection currentConnection)
    {
        this.currentConnection = currentConnection;
    }
    
    public void setSocialConnectionDAO (ISocialConnectionDAO socialConnectionDAO)
    {
        this.socialConnectionDAO = socialConnectionDAO;
    }
    
    public ISocialConnectionDAO getSocialConnectionDAO ()
    {
        return socialConnectionDAO;
    }
    
    @Override
    public void setCurrentSocialConnection (SocialConnection currentSocialConnection)
    {
        this.currentSocialConnection = currentSocialConnection;
    }
    
    @Override
    public SocialConnection getCurrentSocialConnection ()
    {
        return currentSocialConnection;
    }

    @Transactional(readOnly = false)
    @Override
    public void addConnection (SocialConnection connection)
    {
        socialConnectionDAO.addConnection(connection);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteConnection (SocialConnection connection)
    {
        socialConnectionDAO.deleteConnection(connection);
    }

    @Override
    public SocialConnection getConnectionByUserAndType (User user, int networkType)
    {
        return socialConnectionDAO.getConnectionByUserAndType(user, networkType);
    }

    @Override
    public List<SocialConnection> getAllConnectionsByUser (User user)
    {
        return socialConnectionDAO.getAllConnectionsByUser(user);
    }

    @Override
    public SocialConnection getConnectionByProviderUserId(String provider, String providerUserId)
    {
        return socialConnectionDAO.getConnectionByProviderUserId(provider, providerUserId);
    }

    @Override
    public void printOnTheWall(String message)
    {
        Object api = getCurrentConnection().getApi();
        if (api instanceof Facebook)
        {
            try
            {
                Connection<Facebook> connection = (Connection<Facebook>)getCurrentConnection();
                connection.updateStatus(message);
            }
            catch (Exception e)
            {
                Logger.getLogger(SocialConnectionService.class.getName()).log(Priority.WARN, e.getMessage());
            }
        }
        //vk post - is not work because vk don't allow it
    }

    @Override
    public void removeConnectionFromDB (SocialConnection connection)
    {
        /*
        Long userId = connection.getUser().getId();
        ConnectionRepository rep = getUsersConnectionRepository().createConnectionRepository(userId.toString());
        String provider;
        switch (connection.getNetworkType())
        {
            case SocialConnection.FACEBOOK:
                provider = "facebook";
                break;
            case SocialConnection.VKONTAKTE:
                provider = "vkontakte";
                break;
            default:
                provider = "";
        }
        rep.removeConnections(provider);
        */
    }
}
