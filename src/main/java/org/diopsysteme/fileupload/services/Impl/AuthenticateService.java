package org.diopsysteme.fileupload.services.Impl;

import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Data.Repositories.UserRepository;
import org.diopsysteme.fileupload.Web.Dtos.Requests.LoginReqDto;
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

        public User authenticate(LoginReqDto credentials) {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getLogin(),credentials.getPassword()
                )
        );
        return userRepository.findByLogin(credentials.getLogin()).orElseThrow();
    }
}
