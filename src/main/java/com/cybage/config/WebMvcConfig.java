package com.cybage.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.cybage.authentication.MyDBAuthenticationService;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
	 private static final Charset UTF8 = Charset.forName("UTF-8");
	   @Autowired	 
		MyDBAuthenticationService myDBAauthenticationService;
		
	 
	    // Config UTF-8 Encoding.
	    @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
	        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
	        converters.add(stringConverter);
	       	 
	    }
	 
	    // Static Resource Config
	    // equivalents for <mvc:resources/> tags
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	         registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
	        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
	        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	    }
	    
	    @Bean
		public InternalResourceViewResolver viewResolver() {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setPrefix("/WEB-INF/views/jsp/");
			viewResolver.setSuffix(".jsp");
			return viewResolver;
		}	    
	    
	    // equivalent for <mvc:default-servlet-handler/> tag
	    @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
	   
		/*@Autowired
		   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 
		       // For User in database.
		       auth.userDetailsService(myDBAauthenticationService);
		 
		   }
		 protected void configure(HttpSecurity http) throws Exception {
		 
		       http.csrf().disable();
		 
		       // The pages requires login as USER or ADMIN.
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
		               .loginProcessingUrl("/j_spring_security_check") // Submit URL
		               .loginPage("/login")//
		               .defaultSuccessUrl("/accountInfo")//
		               .failureUrl("/login?error=true")//
		               .usernameParameter("userName")//
		               .passwordParameter("password")
		               // Config for Logout Page
		               // (Go to home page).
		               .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		 
		   }
*/		
	 

}
