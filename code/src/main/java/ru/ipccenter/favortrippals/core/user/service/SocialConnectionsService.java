package ru.ipccenter.favortrippals.core.user.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.ipccenter.favortrippals.core.model.SocialConnections;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.dao.ISocialConnectionsDAO;

/**
 * @author Ddiimmaann
 */
public class SocialConnectionsService implements ISocialConnectionsService
{
    ISocialConnectionsDAO socialConnectionsDAO;
    
    public void setSocialConnectionsDAO (ISocialConnectionsDAO socialConnectionsDAO)
    {
        this.socialConnectionsDAO = socialConnectionsDAO;
    }
    
    public ISocialConnectionsDAO getSocialConnectionsDAO ()
    {
        return socialConnectionsDAO;
    }

    @Transactional
    @Override
    public void addConnection(SocialConnections connection)
    {
        socialConnectionsDAO.addConnection(connection);
    }

    @Transactional
    @Override
    public void deleteConnection(SocialConnections connection)
    {
        socialConnectionsDAO.deleteConnection(connection);
    }

    @Override
    public SocialConnections getConnectionByUserAndType(User user, int networkType)
    {
        return socialConnectionsDAO.getConnectionByUserAndType(user, networkType);
    }

    @Override
    public List<SocialConnections> getAllConnectionsByUser(User user)
    {
        return socialConnectionsDAO.getAllConnectionsByUser(user);
    }
}
