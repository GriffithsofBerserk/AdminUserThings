package com.example.AdminUserThings.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class CustomerSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception{

        http.authorizeRequests((authz) -> authz
                        .requestMatchers("/user/**").hasAuthority("USER")
                )
                .formLogin(form -> form
                        .loginPage("/user/login").usernameParameter("email").loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/user/home")
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/user/logout").logoutSuccessUrl("/"));
        return http.build();
    }
}