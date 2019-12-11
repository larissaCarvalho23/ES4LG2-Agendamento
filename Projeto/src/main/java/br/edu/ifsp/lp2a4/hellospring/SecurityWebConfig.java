package br.edu.ifsp.lp2a4.hellospring;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
	   
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            // Para qualquer requisição (anyRequest) é preciso estar 
	            // autenticado (authenticated).
	            .anyRequest().authenticated()
	        .and()
	        .httpBasic();
	  }
	 
	}