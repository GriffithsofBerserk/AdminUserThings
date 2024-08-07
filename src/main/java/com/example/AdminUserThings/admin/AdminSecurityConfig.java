package com.example.AdminUserThings.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class AdminSecurityConfig {

    @Bean
    public CustomerUserDetailsService userDetailsService(){
        return new CustomerUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder1(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider1(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsPasswordService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder1());

        return  provider;
    }
    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception{
        http.authenticationProvider(authenticationProvider1());
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