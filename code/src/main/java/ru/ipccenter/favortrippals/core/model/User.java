package ru.ipccenter.favortrippals.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name="USERS")
public class User {

	private long id;
	private String login;
	private String pass;
	private String email;
	private String name;
	private String surname;
	
	 
	@Id
	@Column(name="ID", unique = true, nullable = false)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	 
	@Column(name="LOGIN", unique = false, nullable = false)
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	
	@Column(name="PASS", unique = false, nullable = false)
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	@PrePersist
	@PreUpdate
	protected void encryptPassword() {
			if (pass != null && (! pass.matches("^[0-9a-fA-F]{40}$"))) {
				// prevent encryption if already encrypted
				pass = DigestUtils.sha1Hex(pass);
			}
	}
	
	
	@Column(name="EMAIL", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	@Column(name="NAME", unique = false, nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	 
	@Column(name="SURNAME", unique = false, nullable = false)
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	} 
	
	 
	 
	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id : ").append(getId());
		strBuff.append(", login : ").append(getLogin());
		strBuff.append(", email : ").append(getEmail());
		strBuff.append(", name : ").append(getName());
		strBuff.append(", surname : ").append(getSurname());
		return strBuff.toString();
	}
 
}
