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
            Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 1");
        String providerUserId = connection.getKey().getProviderUserId();
            Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 2");
        String provider = connection.getKey().getProviderId();
            Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 3");
        User user = getUserService().getUserByProviderUserId(provider, providerUserId);
            Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 4");
        if (user == null)
        {
            user = new User();
            user.setNickname(connection.getDisplayName());
                Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 5");
            getUserService().addUser(user);
                Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 6");
        }
            Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 7");
        SocialConnection socialConnection = 
                    getSocialConnectionService().getConnectionByUserAndType(user, 
                    SocialConnection.getNetworkTypeByString(provider));
            Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 8");
        if (socialConnection == null)
        {
            socialConnection = new SocialConnection();
                Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 9");
            socialConnection.setNetworkType(SocialConnection.getNetworkTypeByString(provider));
                Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 10");
            socialConnection.setProviderUserId(providerUserId);
            socialConnection.setUser(user);
            socialConnection.setUserPage(connection.getProfileUrl());
                Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 11");
            getSocialConnectionService().addConnection(socialConnection);
                Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 12");
        }
            Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n 13");
        return new Long(user.getId()).toString();
    }
}
/*
 *          CharArrayWriter c = new CharArrayWriter();
            PrintWriter p = new PrintWriter(c);
            e.printStackTrace(p);
            Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, "\n" + c.toString());
 */