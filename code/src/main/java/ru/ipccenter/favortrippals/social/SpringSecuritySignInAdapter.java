package ru.ipccenter.favortrippals.social;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.socialconnection.service.ISocialConnectionService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

/**
 * @author Ddiimmaann
 */
@Service
public class SpringSecuritySignInAdapter implements SignInAdapter
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
    
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request)
    {
        Long userId = Long.parseLong(localUserId);
        String provider = connection.getKey().getProviderId();
        User user = getUserService().getUserById(userId);
        getUserService().setCurrentUser(user);
        SocialConnection socialConnection = 
                    getSocialConnectionService().getConnectionByUserAndType(user, 
                    SocialConnection.getNetworkTypeByString(provider));
        getSocialConnectionService().setCurrentSocialConnection(socialConnection);
        getSocialConnectionService().setCurrentConnection(connection);
        return null;
    }
}
