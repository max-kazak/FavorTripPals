package ru.ipccenter.favortrippals.core.socialconnection.dao;

import java.util.List;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;

/**
 * @author Ddiimmaann
 */
public interface ISocialConnectionDAO
{
    public void addConnection (SocialConnection connection);
    public void deleteConnection (SocialConnection connection);
    /**
     * @param userId
     * @param networkType
     * Use constants in SocialConnection
     * @return 
     */
    public SocialConnection getConnectionByUserAndType (User user, int networkType);
    /**
     * Example: provider = "facebook"
     *          providerUserId = "18278498150"
     * @param provider
     * @param providerUserId
     * @return 
     */
    public SocialConnection getConnectionByProviderUserId (String provider, String providerUserId);
    public List<SocialConnection> getAllConnectionsByUser (User user);
}
