package com.cybage.config;

import java.nio.file.DirectoryStream.Filter;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cybage.authentication.MyDBAuthenticationService;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/*@EnableAspectJAutoProxy(proxyTargetClass = true) 
*/@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{   
	@Autowired	 
	MyDBAuthenticationService myDBAauthenticationService;
	
	private ClassLoader beanClassLoader; 
	
	  @Autowired
	   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 
	       // For User in database.
	       auth.userDetailsService(myDBAauthenticationService);
	 
	   }
	 protected void configure(HttpSecurity http) throws Exception {
	 
	     /*  http.csrf().disable();*/
	 
	       //  requires login as USER or ADMIN.
	       // If no login, it will redirect to /login page.
	       http.authorizeRequests().antMatchers("/orderList","/order", "/accountInfo")//
	               .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
	 
	       // For Admin only.
	       http.authorizeRequests().antMatchers("/product").access("hasRole('ROLE_ADMIN')");
	 
	       // When the user has logged in as XX.
	       // But access a page that requires role YY,
	       // AccessDeniedException will throw.
	       http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	 
	       // Config for Login Form
	       http.authorizeRequests().and().formLogin()//
	               // Submit URL of login page.
	               //.loginProcessingUrl("/login") // Submit URL
	               .loginPage("/login")//
	               .defaultSuccessUrl("/accountInfo")//
	               .failureUrl("/login?error=true")//
	               .usernameParameter("userName")//
	               .passwordParameter("password")
	               // Config for Logout Page
	               // (Go to home page).
	               .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	 
	   }
	
	/*@Bean(name="springSecurityFilterChain") 
    public Filter springSecurityFilterChain() throws Exception { 
        boolean hasConfigurers = webSecurityConfigurers != null && !webSecurityConfigurers.isEmpty(); 
        if(!hasConfigurers) { 
            throw new IllegalStateException("At least one non-null instance of "+ WebSecurityConfigurer.class.getSimpleName()+" must be exposed as a @Bean when using @EnableWebSecurity. Hint try extending "+ WebSecurityConfigurerAdapter.class.getSimpleName()); 
        } 
        return webSecurity.build(); */
    } 
	 
	 
	
	
	
