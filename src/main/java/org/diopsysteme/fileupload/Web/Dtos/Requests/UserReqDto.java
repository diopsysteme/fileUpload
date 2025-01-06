package org.diopsysteme.fileupload.Web.Dtos.Requests;

import lombok.Data;
import org.diopsysteme.fileupload.Data.Entities.User;
import prog.dependancy.Validators.UniqueField;

@Data
public class UserReqDto {
    @UniqueField(entity =User.class,field="login")
    private String login;
    private String password;
}
