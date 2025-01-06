package org.diopsysteme.fileupload.Services.Impl;

import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Data.Repositories.UserRepository;
import org.diopsysteme.fileupload.Services.Interfaces.AuthenticationIService;
import org.diopsysteme.fileupload.Web.Dtos.Requests.LoginReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Requests.UserReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.UserResDto;
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
    public User signup(UserReqDto userReqDto){
        return new User();
    }
    public User authenticate(UserReqDto userReqDto){
        return new User();
    }

    @Override
    public User signup(LoginReqDto input) {
        return null;
    }

    @Override
    public User authenticate(LoginReqDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getLogin(),input.getPassword()
                )
        );
        return userRepository.findByLogin(input.getLogin()).orElseThrow();
    }
}
