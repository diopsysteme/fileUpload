package org.diopsysteme.fileupload.services.Interfaces;


import org.diopsysteme.fileupload.domain.entities.User;
import org.diopsysteme.fileupload.model.dtos.requests.LoginRequestDto;

public interface AuthenticationIService {

    public User authenticate(LoginRequestDto input);

}