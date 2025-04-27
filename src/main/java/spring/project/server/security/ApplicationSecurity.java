/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * @author Veljko
 */
//@Configuration
public class ApplicationSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(config -> config
                .requestMatchers(HttpMethod.GET, "/api/**").hasRole(
                        "user")
                .requestMatchers(HttpMethod.POST, "/api/**").hasRole(
                        "user")
                .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("user")
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("admin"));
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT " +
                "username, password, active FROM user_table WHERE " +
                "username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT " +
                "username, role FROM user_table WHERE " +
                "username=?");
        return jdbcUserDetailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
