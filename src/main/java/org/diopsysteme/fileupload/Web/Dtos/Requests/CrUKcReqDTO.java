package org.diopsysteme.fileupload.Web.Dtos.Requests;

import lombok.Data;
import org.keycloak.representations.idm.AbstractUserRepresentation;

import java.util.List;

@Data
public class CrUKcReqDTO   {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;


}
