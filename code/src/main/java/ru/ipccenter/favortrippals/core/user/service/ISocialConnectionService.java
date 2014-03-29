package ru.ipccenter.favortrippals.core.user.service;

import java.util.List;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;

/**
 * @author Ddiimmaann
 */
public interface ISocialConnectionService
{
    public SocialConnection getSocialConnection ();
    public void setSocialConnection (SocialConnection connection);
    public void addConnection (SocialConnection connection);
    public void deleteConnection (SocialConnection connection);
    /**
     * @param userId
     * @param networkType
     * Use constants in SocialConnection
     * @return 
     */
    public SocialConnection getConnectionByUserAndType (User user, int networkType);
    public List<SocialConnection> getAllConnectionsByUser (User user);
}
