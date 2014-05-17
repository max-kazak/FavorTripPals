package ru.ipccenter.favortrippals.web.managedbeans;

import java.util.Map;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

@ManagedBean(name="userMB")
@SessionScoped
public class UserMB
{
    //Spring User Service is injected
    @ManagedProperty(value="#{userService}")
    IUserService userService;

    private User user;

    public void setPicture(String picture)
    {
        checkActuality();
        getUser().setPicture(picture);
        getUserService().updateUser(user);
    }
    
    public void setNickname(String nickname)
    {
        checkActuality();
        getUser().setNickname(nickname);
        getUserService().updateUser(user);
    }

    public void setName(String name)
    {
        checkActuality();
        getUser().setName(name);
        getUserService().updateUser(user);
    }

    public void setEmail(String email)
    {
        checkActuality();
        getUser().setEmail(email);
        getUserService().updateUser(user);
    }
    
    public IUserService getUserService()
    {
        return userService;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    private void checkActuality()
    {
        if(user == null)
            user = getUserService().getCurrentUser();
        if (user == null)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String redirect = "index";
            NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
            myNav.handleNavigation(facesContext, null, redirect);
        }
    }

    public long getId()
    {
        checkActuality();
        return user.getId();
    }

    public String getEmail()
    {
        checkActuality();
        return user.getEmail();
    }
    
    public String getPicture()
    {
        checkActuality();
        if (user.getPicture() == null)
            return "set your photo";
        return user.getPicture();
    }
    
    public String getPictureByUser()
    {
        checkActuality();
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        if (!map.containsKey("userId"))
            return "";
        long userId = Long.parseLong(map.get("userId"));
        User friend = getUserService().getUserById(userId);
        if (friend.getPicture() == null)
            return "set your photo";
        return friend.getPicture();
    }

    public String getName()
    {
        checkActuality();
        return user.getName();
    }
    
    public String getNameByUser()
    {
        checkActuality();
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        if (!map.containsKey("userId"))
            return "";
        long userId = Long.parseLong(map.get("userId"));
        User friend = getUserService().getUserById(userId);
        if (friend.getName() == null)
            return "My dear friend";
        return friend.getName();
    }

    public String getNickname()
    {
        checkActuality();
        return user.getNickname();
    }

    public int getState()
    {
        checkActuality();
        return user.getState();
    }

    public User getUser()
    {
        checkActuality();
        return user;
    }

    @Override
    public String toString()
    {
        checkActuality();
        return user.toString();
    }
}
