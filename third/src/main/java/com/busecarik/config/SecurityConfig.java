package com.busecarik.config;

import com.busecarik.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.busecarik.*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userDetailsService;

    public SecurityConfig(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //TODO: bycrpt versiyona geçir
        try {
            auth.userDetailsService(userDetailsService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/list-users").hasRole("admin")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/")  // after logout redirect to landing page (root)
                    .permitAll();

    }
}
