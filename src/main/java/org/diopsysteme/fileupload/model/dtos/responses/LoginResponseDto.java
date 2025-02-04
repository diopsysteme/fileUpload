package org.diopsysteme.fileupload.model.dtos.responses;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
@Data
public class LoginResponseDto
{
    private String token;
    @Value("${security.jwt.expiration-time}")
    private Long expiresIn;
}
