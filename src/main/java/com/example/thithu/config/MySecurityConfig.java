package com.example.thithu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();
        List<UserDetails> listUser = new ArrayList<>();
        UserDetails user = User.builder()
                .username("congnt")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();
        listUser.add(user);
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
        listUser.add(admin);
        return new InMemoryUserDetailsManager(listUser);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(o -> o.disable())
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/khachHang/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll());
        http.httpBasic(req -> req.setBuilder(http));
        return http.build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//        security.csrf(o -> o.disable()).authorizeHttpRequests(o ->
//                o.requestMatchers("/api/khachhang/**").authenticated().anyRequest().permitAll());
//        security.httpBasic(o -> o.setBuilder(security));
//        return security.build();
//    }

}



