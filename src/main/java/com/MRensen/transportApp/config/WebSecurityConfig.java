package com.MRensen.transportApp.config;

import com.MRensen.transportApp.config.security.JwtRequestFilter;
import com.MRensen.transportApp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    UserDetailsService userDetailsService;

    JwtRequestFilter jwtRequestFilter;


    @Autowired
    WebSecurityConfig(DataSource dataSource, JwtRequestFilter jwtRequestFilter, CustomUserDetailsService userDetailsService){
        this.dataSource = dataSource;
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/customers").hasAnyRole("CUSTOMER", "PLANNER")
                .antMatchers("/customers/**").hasAnyRole("CUSTOMER","PLANNER")
                .antMatchers("/planners").hasRole("PLANNER")
                .antMatchers("/planners/**").hasRole("PLANNER")
                .antMatchers("/drivers").hasAnyRole("PLANNER", "DRIVER")
                .antMatchers("/drivers/**").hasAnyRole("DRIVER", "PLANNER")
                .antMatchers("/routes/**").authenticated()
                .antMatchers("/orders/**").authenticated()
                .antMatchers("/orders/**/**").authenticated()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
