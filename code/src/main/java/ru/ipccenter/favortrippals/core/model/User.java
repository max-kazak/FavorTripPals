package ru.ipccenter.favortrippals.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User implements Serializable
{
    public static final int STATE_ACTIVE = 0;
    public static final int STATE_FOREIGHN = 1;
    public static final int STATE_BLOCKED = 2;

    private Long id;
    private String nickname;
    private String email;
    private String name;
    private Integer state;

    @Id
    @Column(name="ID", unique = true, nullable = false)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
	
    @Column(name="EMAIL", unique = true, nullable = true)
    public String getEmail() 
    {
        return email;
    }
	
    public void setEmail(String email)
    {
        this.email = email;
    }
	
    @Column(name="NAME", unique = false, nullable = true)
    public String getName()
    {
        return name;
    }
	
    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name="NICKNAME", unique = false, nullable = true)
    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    @Column(name="STATE", unique = false, nullable = true)
    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", email : ").append(getEmail());
        strBuff.append(", nickname : ").append(getNickname());
        strBuff.append(", name : ").append(getName());
        strBuff.append(", state : ").append(getState());
        return strBuff.toString();
    }
}
