package org.diopsysteme.fileupload.Web.Dtos.Mappers;

import javax.annotation.processing.Generated;
import org.diopsysteme.fileupload.Web.Dtos.Responses.LoginResDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-08T11:53:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class LoginMapperImpl implements LoginMapper {

    @Override
    public LoginResDto toDto(String token) {
        if ( token == null ) {
            return null;
        }

        LoginResDto loginResDto = new LoginResDto();

        loginResDto.setToken( token );

        return loginResDto;
    }
}
