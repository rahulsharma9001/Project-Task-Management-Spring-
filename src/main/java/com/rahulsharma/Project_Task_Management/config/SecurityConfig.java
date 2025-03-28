package com.rahulsharma.Project_Task_Management.config;

import com.rahulsharma.Project_Task_Management.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests((requests) -> requests
//                .requestMatchers("/project/**").authenticated()
//                .requestMatchers("/tasks/**").authenticated()
//                .requestMatchers("/user/**").permitAll());
//
//        httpSecurity.csrf(csrf -> csrf.disable());
//
////        httpSecurity.formLogin(frmlogin -> frmlogin.disable());
//        httpSecurity.formLogin(Customizer.withDefaults());
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
        return httpSecurity
                .authorizeHttpRequests(registry -> {
//                    registry.requestMatchers("/user/**").permitAll();
                    registry.requestMatchers("/project/**").authenticated();
                    registry.requestMatchers("/tasks/**").authenticated();
                    registry.requestMatchers("/user/**").permitAll();
                })
                .oauth2Login(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("rahul").password(passwordEncoder().encode("1234")).authorities("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
