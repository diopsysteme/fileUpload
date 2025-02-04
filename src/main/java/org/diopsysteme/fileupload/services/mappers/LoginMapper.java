package org.diopsysteme.fileupload.services.mappers;


import org.diopsysteme.fileupload.model.dtos.responses.LoginResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper  {
    LoginResponseDto toDto(String token);
}
