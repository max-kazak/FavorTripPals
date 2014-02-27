package ru.ipccenter.favortrippals.web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;

import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

@ManagedBean(name="loginMB")
@SessionScoped
public class LoginMB {

	//Spring User Service is injected
	@ManagedProperty(value="#{UserService}")
	IUserService userService;
		
	private long id;
	private String login;
	private String email;
	private String name;
	private String surname;	
	
	
	
	public IUserService getUserService() {
		return userService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	private void checkActuality(){
		String actualId = SecurityContextHolder.getContext().getAuthentication().getName(); //id is detted as the name in authentication provider
		if(!actualId.equals(""+id))
		{
			User user = userService.getUserById(Long.parseLong(actualId));
			setId(user.getId());
			setLogin(user.getLogin());
			setEmail(user.getEmail());
			setName(user.getName());
			setSurname(user.getSurname());
		}
	}

	public long getId() {
		checkActuality();
		return id;
	}

	public String getLogin() {
		checkActuality();
		return login;
	}

	public String getEmail() {
		checkActuality();
		return email;
	}

	public String getName() {
		checkActuality();
		return name;
	}

	public String getSurname() {
		checkActuality();
		return surname;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
