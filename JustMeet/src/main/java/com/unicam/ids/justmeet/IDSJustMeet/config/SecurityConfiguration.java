package com.unicam.ids.justmeet.IDSJustMeet.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustomLoginSuccessHandler sucessHandler;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				// URLs matching for access rights
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/welcomepage").permitAll()
				.antMatchers("/about-us").permitAll()
				.antMatchers("/home/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER")
				.antMatchers("/list/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER")
				.antMatchers("/insert-event/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER")
				.antMatchers("/admin/**").hasAnyAuthority("SUPER_USER","ADMIN_USER")
				.antMatchers("/profile-page-admin/**").hasAnyAuthority("SUPER_USER","ADMIN_USER")
				.antMatchers("/insert-event-admin/**").hasAnyAuthority("SUPER_USER","ADMIN_USER")
				.antMatchers("/event-list-admin/**").hasAnyAuthority("SUPER_USER","ADMIN_USER")
				.antMatchers("/list-users-admin/**").hasAnyAuthority("SUPER_USER","ADMIN_USER")
				.antMatchers("/insert-group-admin/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER")
				.antMatchers("/insert-group-user/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER")
				.antMatchers("/my-groups-admin/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER")
				.antMatchers("/my-groups-user/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER")
				.antMatchers("/my-prenotations-admin/**").hasAnyAuthority("SUPER_USER","ADMIN_USER")
				.antMatchers("/my-events-user/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER")
				.antMatchers("/my-prenotations-user/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER")
				.anyRequest().authenticated()
				.and()
				// form login
				.csrf().disable().formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.successHandler(sucessHandler)
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
				// logout
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/welcomepage").and()
				.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}


