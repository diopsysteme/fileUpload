package org.diopsysteme.fileupload.model.dtos.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {
    private String login;
    private String password;
}
