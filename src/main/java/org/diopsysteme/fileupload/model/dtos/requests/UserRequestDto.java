package org.diopsysteme.fileupload.model.dtos.requests;

import lombok.Data;
import org.diopsysteme.fileupload.domain.data.entities.User;
import prog.dependancy.Validators.UniqueField;

@Data
public class UserRequestDto {
    @UniqueField(entity = User.class ,field="login")
    private String login;
    private String password;

}
