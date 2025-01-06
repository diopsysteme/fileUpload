package org.diopsysteme.fileupload.Web.Dtos.Responses;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
@Data
public class LoginResDto
{
    private String token;
    @Value("${security.jwt.expiration-time}")
    private Long expiresIn;
}
