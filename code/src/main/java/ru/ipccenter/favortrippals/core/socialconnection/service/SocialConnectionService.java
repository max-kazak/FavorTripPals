package ru.ipccenter.favortrippals.core.socialconnection.service;

import ru.ipccenter.favortrippals.core.socialconnection.service.ISocialConnectionService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.socialconnection.dao.ISocialConnectionDAO;

/**
 * @author Ddiimmaann
 */
public class SocialConnectionService implements ISocialConnectionService
{
    SocialConnection socialConnection;
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
    public void setSocialConnection (SocialConnection socialConnection)
    {
        this.socialConnection = socialConnection;
    }
    
    @Override
    public SocialConnection getSocialConnection ()
    {
        return socialConnection;
    }

    @Transactional
    @Override
    public void addConnection (SocialConnection connection)
    {
        socialConnectionDAO.addConnection(connection);
    }

    @Transactional
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
}
