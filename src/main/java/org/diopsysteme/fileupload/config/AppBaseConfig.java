package org.diopsysteme.fileupload.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.diopsysteme.fileupload.Data.Enums.StorageType;
import org.diopsysteme.fileupload.Data.Repositories.UserRepository;
import org.diopsysteme.fileupload.Strategy.Impl.StoreDBStrategy;
import org.diopsysteme.fileupload.Strategy.Impl.StoreLocalStrategy;
import org.diopsysteme.fileupload.Strategy.Interfaces.StorageStrategy;
import org.diopsysteme.fileupload.Strategy.Interfaces.StorageWhichInterface;
import org.diopsysteme.fileupload.Strategy.Which.StorageWhich;
import org.diopsysteme.fileupload.Strategy.Which.StorageWhich2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerExceptionResolver;
import prog.dependancy.Filters.JwtAuthenticationFilter;
import prog.dependancy.Services.Impl.EmailServiceImpl;
import prog.dependancy.Services.Impl.JwtService;
import prog.dependancy.Services.Impl.OtpService;
import prog.dependancy.Services.Impl.QRCodeServiceImpl;
import prog.dependancy.Services.Interfaces.EmailService;
import prog.dependancy.Services.Interfaces.IOtpService;
import prog.dependancy.Services.Interfaces.JwtServiceInterface;
import prog.dependancy.Services.Interfaces.QRCodeService;

import java.util.Map;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Your API Title", version = "v1"),
        security = {
                @SecurityRequirement(name = "bearerAuth"),
        })
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class AppBaseConfig {

    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public AppBaseConfig(UserRepository userRepository, HandlerExceptionResolver handlerExceptionResolver) {
        this.userRepository = userRepository;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "appPasswordEncoder")
    public PasswordEncoder appPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    JwtServiceInterface jwtServiceInterface() {
        return new JwtService();
    }
    @Bean
    IOtpService iOtpService(){
        return  new OtpService();
    }
    @Bean
    EmailService emailService(){
        return new EmailServiceImpl();
    }
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtServiceInterface(), userDetailsService(), handlerExceptionResolver);
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    QRCodeService qrCodeService(){
        return new QRCodeServiceImpl();
    }
    @Bean
    public Map<StorageType, StorageStrategy> strategyMap(
            StoreDBStrategy dbStrategy,
            StoreLocalStrategy localStrategy) {
        return Map.of(
                StorageType.DB, dbStrategy,
                StorageType.LOCAL, localStrategy
        );
    }



}
