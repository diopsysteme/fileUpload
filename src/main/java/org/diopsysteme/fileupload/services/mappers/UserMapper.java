package org.diopsysteme.fileupload.services.mappers;

import org.diopsysteme.fileupload.domain.data.entities.User;
import org.diopsysteme.fileupload.model.dtos.requests.UserRequestDto;
import org.diopsysteme.fileupload.model.dtos.responses.UserResponseDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapping;
import prog.dependancy.Web.Mappper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserRequestDto, UserResponseDto> {
    @Mapping(source = "login", target = "username")
    UserRepresentation toKeycloak(User user);
}



