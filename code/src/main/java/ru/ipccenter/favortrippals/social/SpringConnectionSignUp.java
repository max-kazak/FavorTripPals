package ru.ipccenter.favortrippals.social;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.socialconnection.service.ISocialConnectionService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

public final class SpringConnectionSignUp implements ConnectionSignUp
{
    private IUserService userService;
    private ISocialConnectionService socialConnectionService;

    public ISocialConnectionService getSocialConnectionService()
    {
        return socialConnectionService;
    }

    public void setSocialConnectionService(ISocialConnectionService socialConnectionService)
    {
        this.socialConnectionService = socialConnectionService;
    }

    public IUserService getUserService()
    {
        return userService;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    /**
     * Take user from DB if it exist.
     * Else make new user and set to the DB.
     * Set current social connection to the DB if it isn't exist.
     * @param connection
     * @return 
     */
    @Override
    public String execute(Connection<?> connection)
    {
        String providerUserId = connection.getKey().getProviderUserId();
        String provider = connection.getKey().getProviderId();
        User user = getUserService().getUserByProviderUserId(provider, providerUserId);
        if (user == null)
        {
            user = new User();
            user.setNickname(connection.getDisplayName());
            getUserService().addUser(user);
        }
        SocialConnection socialConnection = 
                    getSocialConnectionService().getConnectionByUserAndType(user, 
                    SocialConnection.getNetworkTypeByString(provider));
        if (socialConnection == null)
        {
            socialConnection = new SocialConnection();
            socialConnection.setNetworkType(SocialConnection.getNetworkTypeByString(provider));
            socialConnection.setProviderUserId(providerUserId);
            socialConnection.setUser(user);
            socialConnection.setUserPage(connection.getProfileUrl());
            getSocialConnectionService().addConnection(socialConnection);
        }
        return new Long(user.getId()).toString();
    }
}
/*
 *          CharArrayWriter c = new CharArrayWriter();
            PrintWriter p = new PrintWriter(c);
            e.printStackTrace(p);
            Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, "\n" + c.toString());
 */