package ru.ipccenter.favortrippals.web.managedbeans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ru.ipccenter.favortrippals.core.model.SocialConnection;
import ru.ipccenter.favortrippals.core.socialconnection.service.ISocialConnectionService;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

/**
 * @author Ddiimmaann
 */
@ManagedBean(name="socialConnectionMB")
@SessionScoped
public class SocialConnectionMB
{
    // Field with @ManagedProperty annotation is injected by Spring
    @ManagedProperty (value="#{socialConnectionService}")
    ISocialConnectionService socialConnectionService;
    private String userPage;
    private String provider;
    private String userId;
    private Map<String,String> providers;
    @ManagedProperty (value="#{userService}")
    IUserService userService;

    public IUserService getUserService()
    {
        return userService;
    }

    public void setUserService(IUserService userService)
    {
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
    
    public String getProvider()
    {
        return provider;
    }

    public void setProvider(String provider)
    {
        this.provider = provider;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public Map<String, String> getProviders()
    {
        return providers;
    }
    
    public List<SocialConnection> getConnections ()
    {
        checkActuality();
        return getSocialConnectionService().getAllConnectionsByUser(
                getUserService().getCurrentUser());
    }
    
    private void checkActuality()
    {
        if ((getUserService() == null)||(getUserService().getCurrentUser() == null))
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String redirect = "index";
            NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
            myNav.handleNavigation(facesContext, null, redirect);
        }
    }
    
    public String deleteConnection()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        SocialConnection sc = getSocialConnectionService().getConnectionByProviderUserId(
                            map.get("provider"), map.get("id"));
        SocialConnection current = getSocialConnectionService().getCurrentSocialConnection();
        if (current != null)
            if ((sc.getNetworkType() == current.getNetworkType())&&
                            (sc.getProviderUserId().equals(current.getProviderUserId())))
            {
                //FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(
                    //FacesMessage.SEVERITY_ERROR,"You can't delete your current social connection.", ""));
                return "error";
            }
        getSocialConnectionService().deleteConnection(sc);
        return "success";
    }
    
    public String addConnection ()
    {
        checkActuality();
        if (getProvider() == null)
        {
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,"Select your network.", ""));
            return "error";
        }
        if (getSocialConnectionService().getConnectionByUserAndType(
                            getUserService().getCurrentUser(),
                            SocialConnection.getNetworkTypeByString(getProvider())) != null)
        {
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,"Profile " + getProvider() + " already exist.", ""));
            return "error";
        }
        SocialConnection socialConnection = new SocialConnection();
        socialConnection.setUserPage(getUserPage());
        socialConnection.setUser(getUserService().getCurrentUser());
        socialConnection.setProviderUserId(getUserId());
        socialConnection.setNetworkType(SocialConnection.getNetworkTypeByString(getProvider()));
        getSocialConnectionService().addConnection(socialConnection);
        FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(
                    FacesMessage.SEVERITY_INFO,"Success.", ""));
        return "success";
    }
    
    public SocialConnectionMB ()
    {
        providers = new HashMap<>(); 
        providers.put("facebook", "facebook");
        providers.put("vk", "vk");
        providers.put("twitter", "twitter");
    }
}
