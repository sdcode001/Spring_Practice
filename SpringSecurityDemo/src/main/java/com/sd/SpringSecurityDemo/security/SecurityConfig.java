package com.sd.SpringSecurityDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;


/*
* This class is used for Declarative Security configuration.
* */
@Configuration
public class SecurityConfig {

    /*
    * This is used for in-memory users details in spring-security.
    *
    * Here we're setting users credentials with their roles and these roles can be used
    * for authorized access to API endpoints by using requestMatchers()
    *
    * Using this InMemory security config, To access the API endpoints we have to add
    * username and password in Basic-Auth of http request Authorization
    *
    * Password format- "{id}encodedPassword", where the {id} specifies the PasswordEncoder
    *   id - bcrypt, noop, pbkdf2, scrypt, sha256
    * */
    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user1 = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails user2 = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails user3 = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
    */



    /*
    * This gives Database support to Spring Security for users account info.
    *
    * By default, you have to follow Spring Security's predefined table schemas
    * if you're not using own custom table schemas. And using these predefined
    * tables, Spring Security + JDBC will handle all authentication/authorization logic
    * automatically. No need to write additional code
    *
    * Predefined Tables Schema-
    *  users(username VARCHAR PRIMARY_KEY, password VARCHAR, enabled TINYINT)
    *  authorities(username VARCHAR FOREIGN_KEY REF(users.username), authority VARCHAR) CONSTRAINT UNIQUE_KEY(username, authority)
    *
    *  users.password format- "{id}encodedPassword", where the {id} specifies the PasswordEncoder
    *   id - bcrypt, noop, pbkdf2, scrypt, sha256
    *
    *  authorities.authority values should have "ROLE_" prefix.
    *   Example- "ROLE_EMPLOYEE", "ROLE_ADMIN"
    *
    * Here Datasource object is auto-injected by spring, which refers Database.
    * And JdbcUserDetailsManager manages JDBC authentication logic form users data.
    *
    * Note- For each authorization request it checks in database.
    * */
    /*
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    */


    
    /*
    * This gives Database support to Spring Security with custom tables for user info
    *
    * For security schema customization we just need to:
    *   Provide query to find user by username
    *   Provide query to find authorities by username
    * by using JdbcUserDetailsManager
    *
    * user password format- "{id}encodedPassword", where the {id} specifies the PasswordEncoder
    *   id - bcrypt, noop, pbkdf2, scrypt, sha256
    *
    * authority/role column values should have "ROLE_" prefix.
    *   Example- "ROLE_EMPLOYEE", "ROLE_ADMIN"
    *
    * */
    @Bean
    public UserDetailsManager customUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        //Here ? mark is placeholder and value will be username during login
        manager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        manager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return manager;
    }


    /*
    * Here we're restricting access of different endpoints based on user role
    * by using requestMatchers
    * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){

        //Filter chain
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/page/home").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/page/leaders/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/page/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated() //Any request to the app must be authenticated(Good Practice)
                )
                /*
                * Here we enabled the support for custom login page.
                * If request isn't authenticated then will be redirected to "/auth/showLoginPage" to serve custom login page.
                * After user entered username and password, login page should POST the data to "/authenticateUser" for processing.
                * Spring Security will automatically check username and password on POST to "/authenticateUser" no need to create any Controller.
                * */
                .formLogin(form ->
                        form
                                .loginPage("/auth/showLoginPage")
                                .loginProcessingUrl("/authenticateUser")
                                .permitAll() //Allow everyone to see login page, No need to be logged in.
                )
                /*
                * Add logout support for default URL "/logout"
                * For logout make POST request(through button click) to default "/logout" URL and Spring Security will automatically handle logout.
                * On logout Spring Security will invalidate user HTTP session and remove cookies and redirect to login page.
                * */
                .logout(logout -> logout.permitAll())
                /*
                * Show/redirect user to custom access-denied page, In case of access-denied
                * */
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/auth/access-denied")
                );


        //use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        //disable CSRF protection, As its not required for stateless REST APIs that uses POST,PUT,DELETE/PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
