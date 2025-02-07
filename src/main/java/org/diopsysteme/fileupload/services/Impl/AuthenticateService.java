package org.diopsysteme.fileupload.services.Impl;

import org.diopsysteme.fileupload.domain.entities.User;
import org.diopsysteme.fileupload.repositories.UserRepository;
import org.diopsysteme.fileupload.model.dtos.requests.LoginRequestDto;
import org.diopsysteme.fileupload.services.Interfaces.AuthenticationIService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService implements AuthenticationIService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    public AuthenticateService(AuthenticationManager authenticationManager,UserRepository userRepository)
    {
        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;

    }

        public User authenticate(LoginRequestDto credentials) {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getLogin(),credentials.getPassword()
                )
        );
        return userRepository.findByLogin(credentials.getLogin()).orElseThrow();
    }
}
