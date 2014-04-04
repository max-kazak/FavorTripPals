package ru.ipccenter.favortrippals.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @ManagedProperty(value="#{userService}")
    IUserService userService;
    
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request)
    {
        SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(localUserId, null, null));
        Long userId = Long.parseLong(localUserId);
        User user = userService.getUserById(userId);
        if (user == null)
            user = new User();
        user.setId(userId);
        userService.addUser(user);
        Logger.getLogger(SpringSecuritySignInAdapter.class.getName()).log(Level.INFO, "#NAME:"+connection.getDisplayName()+"#");
        return null;
    }
}
