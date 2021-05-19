
package fs_project.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Configuration for spring security. Determines unauthorised entry-points, as well as authorised ones.
 */

@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurity(UserDetailsService userDetailService) {
        this.userDetailsService = userDetailService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    RestAuthEntryPoint restAuthEntryPoint;

    @Value("${frontend_url}")
    private String frontendUrl;


    @Autowired
    private Environment env;

    @Autowired
    MyAuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // by default uses a Bean by the name of corsConfigurationSource
            .cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/login").authenticated()
            .antMatchers(HttpMethod.POST, "/user").permitAll()
            .antMatchers(HttpMethod.POST, "/user/verify/**").permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            //.antMatchers("/users").hasRole("ADMIN") //ROLE_ADMIN
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(restAuthEntryPoint)
            .and()
            .httpBasic()
            .and()
            .formLogin().loginProcessingUrl("/login")
            .successHandler((request,response,authentication) -> {})
            .failureHandler(failureHandler)
            .and()
            .logout()
            .logoutSuccessHandler((request,response,authentication) -> {})
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID");
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();


        configuration.setAllowedOrigins(Collections.singletonList(frontendUrl));
        configuration.setAllowedHeaders(List.of("*"));

        //configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "OPTIONS"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "OPTIONS", "DELETE"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

