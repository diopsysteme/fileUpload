package org.diopsysteme.fileupload.web.restcontroller;

import org.diopsysteme.fileupload.model.dtos.requests.KeycloakRequestDto;
import org.diopsysteme.fileupload.model.dtos.requests.LoginRequestDto;
import org.diopsysteme.fileupload.services.Impl.KeycloackAdminService;
import org.diopsysteme.fileupload.services.Impl.KeycloackService;
import org.keycloak.representations.idm.UserRepresentation;
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
    public ResponseEntity<Response> token(@RequestBody LoginRequestDto loginRequestDto){
        Response tokenResponseDTO = keycloackAdminService.login(loginRequestDto);
        return ResponseEntity.ok(tokenResponseDTO);
    }
    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody KeycloakRequestDto keycloakRequestDto){
        UserRepresentation user = new UserRepresentation();
        user.setUsername(keycloakRequestDto.getUsername());
        user.setEmail(keycloakRequestDto.getEmail());

         Response createdUser = keycloackAdminService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }


}
