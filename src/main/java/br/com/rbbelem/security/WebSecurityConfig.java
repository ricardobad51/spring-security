package br.com.rbbelem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
	        http.
	                authorizeRequests()
	                    .antMatchers("/webjars/**").permitAll()
	                    .antMatchers("/dados-acesso").hasAnyRole("EDITOR")
	                    .antMatchers("/lista-usuarios").hasAnyRole("ADMIN")
	                    .anyRequest()
	                    .authenticated()
	                    .and()
	                .formLogin()
	                    .loginPage("/login")
	                    .permitAll()
	                    .and()
	                .logout()
	                    .permitAll()
	                    .and()
	                .rememberMe();
	
}
