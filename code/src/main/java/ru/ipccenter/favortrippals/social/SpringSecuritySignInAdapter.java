package ru.ipccenter.favortrippals.social;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

/**
 * @author Ddiimmaann
 */
@Service
public class SpringSecuritySignInAdapter implements SignInAdapter
{
    private IUserService userService;

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
        User user = getUserService().getUserById(userId);
        getUserService().setCurrentUser(user);
        return null;
    }
}
