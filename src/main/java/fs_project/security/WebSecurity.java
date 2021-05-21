
package fs_project.security;


import fs_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Configuration for spring security. Determines unauthorised entry-points, as well as authorised ones.
 */

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurity(UserService userDetailService) {
        this.userDetailsService = userDetailService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    RestAuthEntryPoint restAuthEntryPoint;

    @Autowired
    MyAuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .headers().frameOptions().disable()
                .and()
                .cors()
                .and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/api-docs/**").permitAll()
                .antMatchers("/v3/**").permitAll()
                .antMatchers("/api/swagger-ui/**").permitAll()
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/rooms").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/rooms").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/rooms").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/reservations").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/login").authenticated()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthEntryPoint)
                .and()
                .httpBasic()
                .and()
                .formLogin().loginProcessingUrl("/login")
                .successHandler((request,response,authentication) -> {/*do nothing*/})
                .failureHandler(failureHandler)
                .and()
                .logout()
                .logoutSuccessHandler((request,response,authentication) -> {/*do nothing*/})
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedHeaders(List.of("*"));

        //configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "OPTIONS"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "OPTIONS", "DELETE"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

