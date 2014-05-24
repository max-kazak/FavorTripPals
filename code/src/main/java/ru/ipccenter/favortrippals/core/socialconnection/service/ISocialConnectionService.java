package ru.ipccenter.favortrippals.core.socialconnection.service;

import java.util.List;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;

/**
 * @author Ddiimmaann
 */
public interface ISocialConnectionService
{
    public UsersConnectionRepository getUsersConnectionRepository();
    public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository);
    public SocialConnection getCurrentSocialConnection ();
    public void setCurrentSocialConnection (SocialConnection connection);
    public void setCurrentConnection (Connection connection);
    public Connection getCurrentConnection();
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
    public void printOnTheWall (String message);
}
