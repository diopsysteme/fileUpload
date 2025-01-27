package org.diopsysteme.fileupload.Web.Dtos.Mappers;

import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Web.Dtos.Requests.UserReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.UserResDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapping;
import prog.dependancy.Web.Mappper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserReqDto, UserResDto> {
    @Mapping(source = "login", target = "username")
    UserRepresentation toKeycloak(User user);
}



