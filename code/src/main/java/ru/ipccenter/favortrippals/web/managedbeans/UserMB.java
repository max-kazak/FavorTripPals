package ru.ipccenter.favortrippals.web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

@ManagedBean(name="userMB")
@SessionScoped
public class UserMB {

	//Spring User Service is injected
	@ManagedProperty(value="#{UserService}")
	IUserService userService;
		
	private User user;
	
	public IUserService getUserService() {
		return userService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	private void checkActuality(){
		String actualId = SecurityContextHolder.getContext().getAuthentication().getName(); //id is detted as the name in authentication provider
		if( (user==null) || ( !actualId.equals(""+user.getId())) )
		{
			user = userService.getUserById(Long.parseLong(actualId));
		}
	}

    public long getId() {
        checkActuality();
        return user.getId();
    }


    public String getEmail() {
        checkActuality();
        return user.getEmail();
    }

    public String getName() {
        checkActuality();
        return user.getName();
    }

    public String getNickname() {
        checkActuality();
        return user.getNickname();
    }

    public int getState() {
        checkActuality();
        return user.getState();
    }

    @Override
    public String toString() {
        checkActuality();
        return user.toString();
    }

}
