package ru.ipccenter.favortrippals.web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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

    public void setNickname(String nickname)
    {
        getUser().setNickname(nickname);
        getUserService().updateUser(user);
    }

    public void setName(String name)
    {
        getUser().setName(name);
        getUserService().updateUser(user);
    }

    public void setEmail(String email)
    {
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
        {
            user = getUserService().getCurrentUser();
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
        if ((user.getEmail() == null)||(user.getEmail().length() == 3))
            return "set your email";
        return user.getEmail();
    }

    public String getName()
    {
        checkActuality();
        if ((user.getName() == null)||(user.getName().length() == 3))
            return "set your name";
        return user.getName();
    }

    public String getNickname()
    {
        checkActuality();
        if ((user.getNickname() == null)||(user.getNickname().length() == 3))
            return "set your nickname";
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
