package org.diopsysteme.fileupload.Web.Dtos.Requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginReqDto {
    private String login;
    private String password;
}
