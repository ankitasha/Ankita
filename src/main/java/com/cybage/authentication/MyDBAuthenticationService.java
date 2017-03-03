package com.cybage.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cybage.dao.AccountDao;
import com.cybage.entity.Account;

@Service
public class MyDBAuthenticationService implements UserDetailsService
{
	/*@Autowired*/
	private AccountDao accountDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
	{
	 Account account= accountDao.findAccount(userName);
	 System.out.println("Account=" + account);
      if(account== null)
      {
    	throw new UsernameNotFoundException("User"+userName+"not found")  ;
      }
	  
      String role= account.getUserRole();
      
      List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
	  GrantedAuthority authority= new SimpleGrantedAuthority("Role_"+role);
	  grantList.add(authority);
	  
	  boolean enabled= account.isActive();
	  boolean accountNonExpired = true;
	  boolean credentialsNonExpired= true;
	  boolean accountNonLocked = true;
	  
	  UserDetails userDetails = new User(account.getUserName(), account.getPassword(),
			  enabled, accountNonExpired,
			  credentialsNonExpired, accountNonLocked, 
			  grantList);
	  
		return userDetails;
	}
	
	
	
	

}
