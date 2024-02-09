package com.example.registrationlogindemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/users").authenticated()
                                .requestMatchers("/items").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/staff").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/shops").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/edit/**").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/editstaff/**").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/additem/**").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/saveitem/**").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/deleteitem/**").hasAnyRole("ADMIN","ROLE_ADMIN")
                                .requestMatchers("/profile/**").authenticated()
                                .requestMatchers("/edituser/**").hasAnyRole("ADMIN","ROLE_ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/login").permitAll()
                                .loginProcessingUrl("/login").permitAll()
                                .defaultSuccessUrl("/profile")
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}