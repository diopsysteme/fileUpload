package org.diopsysteme.fileupload.Web.Dtos.Mappers;


import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Web.Dtos.Requests.LoginReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.LoginResDto;
import org.mapstruct.Mapper;
import prog.dependancy.Services.Interfaces.JwtServiceInterface;
import prog.dependancy.Web.Mappper.GenericMapper;

@Mapper(componentModel = "spring")
public interface LoginMapper  {
    LoginResDto toDto(String token);
}
