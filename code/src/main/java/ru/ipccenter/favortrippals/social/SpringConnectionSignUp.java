package ru.ipccenter.favortrippals.social;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.vkontakte.api.VKontakte;
import ru.ipccenter.favortrippals.core.friendship.service.IFriendshipService;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.socialconnection.service.ISocialConnectionService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;
import ru.ipccenter.favortrippals.core.user.service.UserService;

public final class SpringConnectionSignUp implements ConnectionSignUp
{
    private IUserService userService;
    private ISocialConnectionService socialConnectionService;
    private IFriendshipService friendshipService;

    public IFriendshipService getFriendshipService()
    {
        return friendshipService;
    }

    public void setFriendshipService(IFriendshipService friendshipService)
    {
        this.friendshipService = friendshipService;
    }

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
        if (getUserService().getCurrentUser() != null)
            UserService.removeCurrent();
        
        String providerUserId = connection.getKey().getProviderUserId();
        String provider = connection.getKey().getProviderId();
        
        User user = getUserService().getUserByProviderUserId(provider, providerUserId);
        String email = connection.fetchUserProfile().getEmail();
        if (user == null && email != null)
            user = getUserService().getUserByEmail(email);
        if (user == null)
        {
            user = new User();
            user.setNickname(connection.getDisplayName());
            user.setName(connection.getDisplayName());
            if (email == null)
                user.setEmail("Not set.");
            else
                user.setEmail(email);
            user.setPicture(connection.getImageUrl());
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

            switch (provider.toLowerCase())
            {
                case "facebook":
                    Facebook fb = (Facebook)connection.getApi();
                    byte[] im = fb.userOperations().getUserProfileImage(ImageType.LARGE);
                    try
                    {
                        String path = File.separator + "resources" +
                                            File.separator + providerUserId + ".jpg";
                        File outFile = new File(".." + File.separator + "webapps" + File.separator + "favortrippals" + path);
                        if (outFile.exists())
                        {
                            user.setPicture("." + path);
                            getUserService().updateUser(user);
                        }
                        outFile.createNewFile();
                        FileOutputStream fout = new FileOutputStream(outFile);
                        fout.write(im);
                        fout.close();
                        user.setPicture("." + path);
                        getUserService().updateUser(user);
                    }
                    catch (Exception ex)
                    {
                        Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "vk": case "vkontakte":
                    VKontakte vk = (VKontakte)connection.getApi();
                    user.setPicture(vk.usersOperations().getUser().getPhotoBig());
                    getUserService().updateUser(user);
                    break;
            }
        }
        getUserService().setCurrentUser(user);
        return new Long(user.getId()).toString();
    }
}
/*
 *          CharArrayWriter c = new CharArrayWriter();
            PrintWriter p = new PrintWriter(c);
            e.printStackTrace(p);
            Logger.getLogger(SpringConnectionSignUp.class.getName()).log(Level.WARNING, "\n" + c.toString());
 */