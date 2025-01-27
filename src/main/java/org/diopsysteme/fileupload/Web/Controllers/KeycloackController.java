package org.diopsysteme.fileupload.Web.Controllers;

import org.diopsysteme.fileupload.Web.Dtos.Requests.CrUKcReqDTO;
import org.diopsysteme.fileupload.Web.Dtos.Requests.LoginReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.TokenResponseDTO;
import org.diopsysteme.fileupload.services.Impl.KeycloackAdminService;
import org.diopsysteme.fileupload.services.KeycloackService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.ws.rs.core.Response;

@RestController
@RequestMapping("/auth")
public class KeycloackController {
    private KeycloackService keycloakService;
    private KeycloackAdminService keycloackAdminService;
    public KeycloackController(KeycloackService keycloakService,KeycloackAdminService keycloackAdminService){
        this.keycloakService=keycloakService;
        this.keycloackAdminService = keycloackAdminService;
    }
    @PostMapping
    public ResponseEntity<Response> token(@RequestBody LoginReqDto loginReqDto){
        Response tokenResponseDTO = keycloackAdminService.login(loginReqDto);
        return ResponseEntity.ok(tokenResponseDTO);
    }
    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody CrUKcReqDTO crUKcReqDTO){
        UserRepresentation user = new UserRepresentation();
        user.setUsername(crUKcReqDTO.getUsername());
        user.setEmail(crUKcReqDTO.getEmail());

         Response createdUser = keycloackAdminService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }


}
