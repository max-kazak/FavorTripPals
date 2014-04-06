package ru.ipccenter.favortrippals.core.socialconnection.service;

import java.util.List;
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
    SocialConnection currentSocialConnection;
    ISocialConnectionDAO socialConnectionDAO;
    
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
}
