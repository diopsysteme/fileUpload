package org.diopsysteme.fileupload.Services.Interfaces;


import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Web.Dtos.Requests.LoginReqDto;

public interface AuthenticationIService {
    public User signup(LoginReqDto input);
    public User authenticate(LoginReqDto input);

}