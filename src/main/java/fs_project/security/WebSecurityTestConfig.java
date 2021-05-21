package fs_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(2)
public class WebSecurityTestConfig extends WebSecurityConfigurerAdapter {

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

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test")
                .password("{noop}test")
                .roles("TEST")
                .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .and()
                .withUser("1234")
                .password("{noop}1234")
                .roles("USER");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        /*final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);

        return source;*/

    /*
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedHeaders(List.of("*"));

        //configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "OPTIONS"));

        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "OPTIONS", "DELETE"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }*/

    @Bean
    @Override
    @Primary
    public UserDetailsService userDetailsService() {

        //User Role
        UserDetails user = User.withUsername("1234")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
//                .passwordEncoder(getPasswordEncoder()::encode)
                .password("1234").roles("USER").build();

        //Manager Role
        UserDetails test = User.withUsername("test@test.no")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
//                .passwordEncoder(getPasswordEncoder()::encode)
                .password("test1234").roles("ADMIN").build();

        // Admin
        UserDetails admin = User.withUsername("admin")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
//                .passwordEncoder(getPasswordEncoder()::encode)
                .password("admin").roles("ADMIN").build();


        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(user);
        userDetailsManager.createUser(test);
        userDetailsManager.createUser(admin);

        return userDetailsManager;
    }

}
