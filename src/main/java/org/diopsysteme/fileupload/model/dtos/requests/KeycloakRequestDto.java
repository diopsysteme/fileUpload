package org.diopsysteme.fileupload.model.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
//TODO  preferer des nom de classe parlant
public class KeycloakRequestDto {
    @Schema(description = "donner un user", example = "user")
    private String username;
    @Schema(description = "donner un password ", example = "2Xx@1234")
    private String password;
    @Schema(description = "donner un email", example = "mail@mail.com")
    private String email;
    @Schema(description = "donner un prenom", example = "prenom")
    private String firstName;
    @Schema(description = "donner un nom", example = "nom")
    private String lastName;
    @Schema(description = "donner un role", example = "ROLE_USER")
    private List<String> roles;


}
