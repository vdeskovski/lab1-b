package com.example.lab1b.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
        http
                .csrf().disable()
                .authorizeHttpRequests( (requests) -> requests
                                //.requestMatchers(PathRequest.toH2Console()).permitAll()
                                .anyRequest().permitAll()
//                                .requestMatchers(PathRequest.toH2Console()).permitAll()
//                        //requests.requestMatchers(PathRequest.toH2Console()).p
//                        requests.anyRequest().permitAll()


                );
        http.headers().frameOptions().disable();

//        http
//                .csrf().disable()
//                .authorizeHttpRequests( (requests) -> requests
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/accommodation/add"),
//                                AntPathRequestMatcher.antMatcher("/api/accommodation/edit/**"),
//                                AntPathRequestMatcher.antMatcher("/api/accommodation/delete/**"),
//                                AntPathRequestMatcher.antMatcher("/api/accommodation/rent/**")).hasRole("ADMIN")
//                        .anyRequest().permitAll()
//                )
//                .formLogin((form) -> form
//                        .permitAll()
//                        .failureUrl("/login?error=BadCredentials")
//                        .defaultSuccessUrl("/api/accommodation", true)
//                )
//                .logout((logout) -> logout
//                        .logoutUrl("/logout")
//                        .clearAuthentication(true)
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/")
//                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
