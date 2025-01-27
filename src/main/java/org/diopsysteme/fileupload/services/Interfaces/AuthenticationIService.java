package org.diopsysteme.fileupload.services.Interfaces;


import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Web.Dtos.Requests.LoginReqDto;

public interface AuthenticationIService {

    public User authenticate(LoginReqDto input);

}