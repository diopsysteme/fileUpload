package org.diopsysteme.fileupload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConf2 {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**","/error","/swagger-ui/**","/v1/api-docs/**","/v3/api-docs/**","/swagger-resources/**","/error/**","/webjars/","/api-docs/**","/user","/login","/monitoring/**","/api/public/**")
               .permitAll()
                .anyRequest()


                .authenticated();

        httpSecurity
                .oauth2ResourceServer()
                .jwt();
                httpSecurity.
                        sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                return httpSecurity.build();
    }
}
