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

    public static final int STATE_ACTIVE = 0;
    public static final int STATE_FOREIGHN = 1;
    public static final int STATE_BLOCKED = 2;

	private long id;
	private String nickname;
	private String pass;
	private String email;
	private String name;
        private int state;

	@Id
	@Column(name="ID", unique = true, nullable = false)
	public long getId() {
                return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="PASS", unique = false, nullable = false)
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = encryptPassword(pass);
	}

	protected String encryptPassword(String pass) {
			if (pass != null && (! pass.matches("^[0-9a-fA-F]{40}$"))) {
				// prevent encryption if already encrypted
				return DigestUtils.sha1Hex(pass);
			} else return pass;
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

    @Column(name="NICKNAME", unique = false, nullable = true)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name="STATE", unique = false, nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", email : ").append(getEmail());
        strBuff.append(", nickname : ").append(getNickname());
        strBuff.append(", name : ").append(getName());
        strBuff.append(", state : ").append(getState());
        return strBuff.toString();
    }
 
}
