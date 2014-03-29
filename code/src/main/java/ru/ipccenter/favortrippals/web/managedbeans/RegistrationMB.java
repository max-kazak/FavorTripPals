package ru.ipccenter.favortrippals.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.service.IUserService;


@ManagedBean(name="registrationMB")
@RequestScoped
public class RegistrationMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";

	//Spring User Service is injected
	@ManagedProperty(value="#{UserService}")
	IUserService userService;
	
	List<User> userList;
	
	private long id;
	private String pass;
	private String email;
	private String name;
	
	
	public String registerUser() {
		try {
			User user = new User();
			user.setPass(getPass());
			user.setEmail(getEmail());
			user.setName(getName());
			getUserService().addUser(user);
			return SUCCESS;
		} catch (DataAccessException e) {
            System.out.println(e);
		}
		
		return ERROR;
	}


	public void reset() {
		this.setId(0);
		this.setPass("");
		this.setEmail("");
		this.setName("");
	}
	
	
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}
	
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
	
	public IUserService getUserService() {
		return userService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}


	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


}
