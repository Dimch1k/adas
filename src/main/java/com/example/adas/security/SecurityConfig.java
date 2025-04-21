package com.example.adas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer"))
                .roles("CUSTOMER")
                .build();

        UserDetails analyst = User.builder()
                .username("analyst")
                .password(passwordEncoder().encode("analyst"))
                .roles("ANALYST")
                .build();

        UserDetails projectManager = User.builder()
                .username("projectManager")
                .password(passwordEncoder().encode("projectManager"))
                .roles("PROJECT_MANAGER")
                .build();

        UserDetails developer = User.builder()
                .username("developer")
                .password(passwordEncoder().encode("developer"))
                .roles("DEVELOPER")
                .build();

        UserDetails qa = User.builder()
                .username("qa")
                .password(passwordEncoder().encode("qa"))
                .roles("QA")
                .build();

        UserDetails support = User.builder()
                .username("support")
                .password(passwordEncoder().encode("support"))
                .roles("SUPPORT")
                .build();

        return new InMemoryUserDetailsManager(admin, customer, analyst, projectManager, developer, qa, support);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/customers.html").hasAnyRole("ADMIN", "ANALYST", "SUPPORT")
                        .requestMatchers("/requirements.html").hasAnyRole("ADMIN", "CUSTOMER", "ANALYST", "PROJECT_MANAGER", "DEVELOPER", "QA")
                        .requestMatchers("/specifications.html").hasAnyRole("ADMIN", "CUSTOMER", "ANALYST", "PROJECT_MANAGER", "DEVELOPER", "QA", "SUPPORT")
                        .requestMatchers("/developments.html").hasAnyRole("ADMIN", "CUSTOMER", "ANALYST", "PROJECT_MANAGER", "DEVELOPER", "QA", "SUPPORT")
                        .requestMatchers("/testings.html").hasAnyRole("ADMIN", "CUSTOMER", "ANALYST", "PROJECT_MANAGER", "DEVELOPER", "QA", "SUPPORT")
                        .requestMatchers("/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults()) // spring security form
//                .httpBasic(Customizer.withDefaults()); // popup form
                .exceptionHandling(eh -> eh
                        .accessDeniedPage("/access-denied")
                );


        return http.build();
    }
}
