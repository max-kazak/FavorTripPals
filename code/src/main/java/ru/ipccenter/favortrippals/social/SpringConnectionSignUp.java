package ru.ipccenter.favortrippals.social;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FriendOperations;
import org.springframework.social.facebook.api.ImageType;
import ru.ipccenter.favortrippals.core.friendship.service.IFriendshipService;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.socialconnection.service.ISocialConnectionService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

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
        String providerUserId = connection.getKey().getProviderUserId();
        String provider = connection.getKey().getProviderId();
        User user = getUserService().getUserByProviderUserId(provider, providerUserId);
        if (user == null)
        {
            user = new User();
            user.setNickname(connection.getDisplayName());
            user.setName(connection.getDisplayName());
            user.setEmail("Not set.");
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
        }

        switch (provider.toLowerCase())
        {
            case "facebook":
                Facebook fb = (Facebook)connection.getApi();
                FriendOperations fo = fb.friendOperations();
                List<String> friendIds = fo.getFriendIds();
                for (String fId : friendIds)
                {
                    User friend = getUserService().getUserByProviderUserId(provider, fId);
                    if (friend != null)
                        getFriendshipService().createFriendship(user, friend);
                }
                
                byte[] im = fb.userOperations().getUserProfileImage(ImageType.LARGE);
                try
                {
                    String path = File.separator + "resources" +
                                        File.separator + providerUserId + ".jpg";
                    File outFile = new File(".." + File.separator + "webapps" + File.separator + "favortrippals" + path);
                    if (!outFile.exists())
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
                break;
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