package org.diopsysteme.fileupload.Web.Dtos.Mappers;

import javax.annotation.processing.Generated;
import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Web.Dtos.Requests.UserReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.UserResDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-08T11:40:17+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserResDto userResDto = new UserResDto();

        userResDto.setId( entity.getId() );
        userResDto.setLogin( entity.getLogin() );

        return userResDto;
    }

    @Override
    public User toEntity(UserReqDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setLogin( dto.getLogin() );
        user.setPassword( dto.getPassword() );

        return user;
    }
}
