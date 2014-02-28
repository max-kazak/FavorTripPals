package ru.ipccenter.favortrippals.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ru.ipccenter.favortrippals.core.model.User;
import ru.ipccenter.favortrippals.core.user.dao.IUserDAO;
import ru.ipccenter.favortrippals.core.user.service.IUserService;

@Service("wreAuthenticationProvider")
public class ftpAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private String adminUser;
	private String adminPassword;
	  
	private IUserService userService;
	
	
	//DI with Spring
	@Required
	public void setAdminUser(String adminUser) {
	  this.adminUser = adminUser;
	}
	  
	@Required
	public void setAdminPassword(String adminPassword) {
	  this.adminPassword = adminPassword;
	}
	
	@Required
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1) throws AuthenticationException {
		return;
	}

	@Override
	protected UserDetails retrieveUser(String email, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		long id = 0;
		
		String password = (String) authentication.getCredentials();
	    if (! StringUtils.hasText(password)) {
	      throw new BadCredentialsException("Please enter password");
	    }
	    
	    String encryptedPassword = DigestUtils.sha1Hex(password); 
	    
	    String expectedPassword = null;
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    
	    if (adminUser.equals(email)) {
	      // pseudo-user admin (ie not configured via Person)
	      expectedPassword = DigestUtils.sha1Hex(adminPassword); 
	      // authenticate admin
	      if (! encryptedPassword.equals(expectedPassword)) {
	        throw new BadCredentialsException("Invalid password");
	      }
	      // authorize admin
	      authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	    } else {
	      
	    try {
	        User user = userService.getUserByEmail(email);
            if(user==null)
                throw  new EntityNotFoundException("user with such email don't exist");

	        id = user.getId();
	        // authenticate the person
	        expectedPassword = user.getPass();
	        
	        if (! StringUtils.hasText(expectedPassword)) {
	          throw new BadCredentialsException("No password for " + email + " set in database, contact administrator");
	        }
	        if (! encryptedPassword.equals(expectedPassword)) {
	          throw new BadCredentialsException("Invalid Password");
	        }
	        
	        // authorize the person
	        authorities.add((new SimpleGrantedAuthority("ROLE_USER")));
	      } catch (EntityNotFoundException e) {
	        throw new BadCredentialsException("Invalid user");
	      } catch (NonUniqueResultException e) {
	        throw new BadCredentialsException(
	          "Non-unique user, contact administrator");
	      }
	    }
	    return new org.springframework.security.core.userdetails.User(
	      ""+id,
	      password,
	      true, // enabled 
	      true, // account not expired
	      true, // credentials not expired 
	      true, // account not locked
	      authorities
	    );
	}

}
