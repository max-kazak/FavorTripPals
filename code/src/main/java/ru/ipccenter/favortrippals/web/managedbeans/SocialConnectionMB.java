package ru.ipccenter.favortrippals.web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.socialconnection.service.ISocialConnectionService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

/**
 * @author Ddiimmaann
 */
@ManagedBean(name="socialConnectionMB")
@RequestScoped
public class SocialConnectionMB
{
    // Field with @ManagedProperty annotation is injected by Spring
    @ManagedProperty (value="#{socialConnectionService}")
    ISocialConnectionService socialConnectionService;
    private String userPage;
    private int networkType = SocialConnection.FACEBOOK;
    @ManagedProperty (value="#{userService}")
    IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
	
    public void setSocialConnectionService (ISocialConnectionService socialConnectionService)
    {
        this.socialConnectionService = socialConnectionService;
    }
    
    public ISocialConnectionService getSocialConnectionService ()
    {
        return socialConnectionService;
    }
    
    public void setUserPage (String userPage)
    {
        this.userPage = userPage;
    }
    
    public String getUserPage ()
    {
        return userPage;
    }
    
    public String addConnection ()
    {
        SocialConnection socialConnections = new SocialConnection();
        socialConnections.setNetworkType(networkType);
        socialConnections.setUserPage(getUserPage());
        socialConnections.setUser(getUserService().getCurrentUser());
        getSocialConnectionService().addConnection(socialConnections);
        return "success";
    }
}
