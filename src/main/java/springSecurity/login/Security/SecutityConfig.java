package springSecurity.login.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import springSecurity.login.Security.Filters.AuthenticationFilter;
import springSecurity.login.Security.Filters.AuthorizationFilter;
import springSecurity.login.Utils.JwtUtils;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecutityConfig {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthorizationFilter authorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(jwtUtils);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        http
            .csrf(csrfConfig -> csrfConfig.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->{
                auth.requestMatchers("/login").permitAll();
                auth.requestMatchers("/signIn").permitAll();
                auth.requestMatchers("/user/create").permitAll();
                auth.requestMatchers("/").permitAll();
                // auth.requestMatchers("/redirectPanel").permitAll();
                auth.requestMatchers("/panel").permitAll();
                auth.requestMatchers("/panelAdmin").permitAll();
                auth.requestMatchers("/views/**","/js/**","/css/**","/images/**").permitAll();
                auth.requestMatchers("/user/updateRol/**").hasRole("ADMIN");
                // auth.requestMatchers("/redirect").permitAll();
                auth.anyRequest().authenticated();  
                })
            // .authenticationProvider(authenticationProvider)
            // .httpBasic(Customizer.withDefaults())
            .addFilter(authenticationFilter)
            .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
            ;
        return http.build();
    } 


}
