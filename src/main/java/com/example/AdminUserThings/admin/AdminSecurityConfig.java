package com.example.AdminUserThings.admin;

import com.example.AdminUserThings.services.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class AdminSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomerUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception{

        //http.authorizeRequests().antMatchers("/").permitAll();
        //http.antMatchers("/admin/**").authorizeRequests().anyRequest().hasAuthority("ADMIN").and().formLogin().loginPage("/admin/login").usernameParameters("email").loginProcessingUrl("/admin/login").defaultSuccessUrl("/admin/home").permitAll().and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/");

        http.authorizeRequests((authz) -> authz
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/admin/login").usernameParameter("email").loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin/home")
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/admin/logout").logoutSuccessUrl("/"));
        return http.build();
    }
}