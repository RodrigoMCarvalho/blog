package com.rodrigo.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService UserDetailsService;

    private static final String[] AUTH_LIST = {
            "/blog",
            "/blog/posts",
            "/blog/posts/{id}"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/blog/login")
                    .usernameParameter("login")
                    .passwordParameter("senha").permitAll()
                .and()
                    .formLogin().permitAll()
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        /*auth.inMemoryAuthentication()
                .withUser("rodrigo").password("{noop}123").roles("ADMIN");*/
        auth.userDetailsService(UserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
