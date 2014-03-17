package ru.ipccenter.favortrippals.web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.ipccenter.favortrippals.core.model.SocialConnections;
import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.service.ISocialConnectionsService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;
import ru.ipccenter.favortrippals.core.user.service.UserService;

/**
 * @author Ddiimmaann
 */
@ManagedBean(name="socialConnectionsMB")
@RequestScoped
public class SocialConnectionsMB
{
    @ManagedProperty(value="#{SocialConnectionsService}")
    ISocialConnectionsService socialConnectionsService;
    private String userPage;
    private int networkType = SocialConnections.FACEBOOK;
    
    //Spring User Service is injected
    @ManagedProperty(value="#{UserService}")
    IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
	
    public void setSocialConnectionsService (ISocialConnectionsService socialConnectionsService)
    {
        this.socialConnectionsService = socialConnectionsService;
    }
    
    public ISocialConnectionsService getSocialConnectionsService ()
    {
        return socialConnectionsService;
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
        SocialConnections socialConnections = new SocialConnections();
        socialConnections.setNetworkType(networkType);
        socialConnections.setUserPage(getUserPage());
        String actualId = SecurityContextHolder.getContext().getAuthentication().getName(); //id is detted as the name in authentication provider
        socialConnections.setUser(userService.getUserById(Long.parseLong(actualId)));
        getSocialConnectionsService().addConnection(socialConnections);
        return "success";
    }
}
