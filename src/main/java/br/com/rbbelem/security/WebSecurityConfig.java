package br.com.rbbelem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
	        http.
	                authorizeRequests()
	                	.antMatchers("/webjars/**").permitAll()
	                    .anyRequest()
	                    .authenticated()
	                    .and()
	                .formLogin()
	                    .loginPage("/login")
	                    .permitAll()
	                    .and()
	                .logout()
	                	.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder buAuthenticationManagerBuilder) throws Exception{
		buAuthenticationManagerBuilder
				.inMemoryAuthentication()
				.withUser("ricardo").password("$2a$10$QenvwWveq91j/H44oOIMj.wpPK3Nk05E3PyRo7I0iiHL18B79SONa").roles("EDITOR","ADMIN")
				.and()
				.withUser("belem").password("$2a$10$sz7INgjfY.AYqb/Nb5dBXug.BbJeHqhS/WhGFNchbrqnQEmB7p7v.").roles("EDITOR");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
}
