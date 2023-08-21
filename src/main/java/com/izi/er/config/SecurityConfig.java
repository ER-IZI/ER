package com.izi.er.config;

import com.izi.er.config.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests(authorize -> authorize
                .antMatchers("/admin/test").hasRole("ADMIN")
                .antMatchers("/ordinary/test").hasAnyRole("ADMIN", "ORDINARY")
                .antMatchers("/ambulance/test").hasAnyRole("ADMIN", "AMBULANCE")
                .antMatchers("/hospital/test").hasAnyRole("ADMIN", "HOSPITAL")
                .antMatchers("/**").permitAll()
            )
            .formLogin(form -> form.disable())
            .addFilterBefore(new JwtAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

