package org.diopsysteme.fileupload.services.Impl;

import org.diopsysteme.fileupload.model.dtos.requests.KeycloakRequestDto;
import org.diopsysteme.fileupload.model.dtos.requests.LoginRequestDto;
import org.diopsysteme.fileupload.services.Interfaces.KeycloackAdminIService;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;


import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;

@Service
public class KeycloackAdminService implements KeycloackAdminIService {
    @Value("${spring.security.oauth2.client.registration.keycloak.realm}")
    private String realm;
    @Value("${spring.security.oauth2.client.registration.keycloak.auth-server-url}")
    private String authServerUrl;
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;
    Keycloak keycloak;
    public KeycloackAdminService(Keycloak keycloak){
        this.keycloak = keycloak;
    }



    @Override
    public Response createUser(UserRepresentation userRepresentation) {
        try {
            var realmResource = keycloak.realm(realm);
            System.out.println("Successfully connected to Keycloak realm: " + realm);
            if (userRepresentation.getUsername() == null || userRepresentation.getUsername().isEmpty()) {
                throw new IllegalArgumentException("Username is required.");
            }

            if (userRepresentation.getCredentials() == null || userRepresentation.getCredentials().isEmpty()) {
                throw new IllegalArgumentException("Password is required.");
            }
            userRepresentation.setEnabled(true);
            Response res = realmResource.users().create(userRepresentation);
            return handleResponse(res);
        } catch (Exception e) {
            System.err.println("Exception during user creation: " + e.getMessage());
            throw new RuntimeException("Failed to create user in Keycloak. Reason: " + e.getMessage(), e);
        }
    }
//TODO HTTP STATUS
    private Response handleResponse(Response res) {
        if (res.getStatus() >= 200 && res.getStatus() < 300) {
            System.out.println("User successfully created. Status: " + res.getStatus());
            return res;
        } else {
            String errorMessage = res.readEntity(String.class);
            System.err.println("Failed to create user. Status: " + res.getStatus() + ", Message: " + errorMessage);
            throw new RuntimeException("Error from Keycloak: " + errorMessage);
        }
    }
    public Response login(LoginRequestDto loginRequestDto){
        try {
                Keycloak keycloak = keycloackCredentials(loginRequestDto);
                System.out.println("Successfully connected to Keycloak realm: " + keycloak.toString()   );
                AccessTokenResponse token = keycloackCredentials(loginRequestDto).tokenManager().getAccessToken();

            if (token != null) {
                System.out.println("Successfully logged in. Token: " + token);
                return Response.ok().entity(token).build();
            } else {
                System.err.println("Failed to log in. Token is null.");
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            System.err.println("Exception during login: " + e.getMessage());
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    private Keycloak keycloackCredentials(LoginRequestDto loginRequestDto){
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .clientId(clientId)
                .grantType(OAuth2Constants.PASSWORD)
                .username(loginRequestDto.getLogin())
                .password(loginRequestDto.getPassword())
                .build();

    }

    @Override
    public Response updateUser(Long id, KeycloakRequestDto keycloakRequestDto) {
        return null;
    }

    @Override
    public Response deleteUser(Long id) {
        return null;
    }

    @Override
    public UserRepresentation getUser(Long id) {
        return null;
    }

    @Override
    public Response getUsers() {
        return null;
    }

    @Override
    public Response getRoles() {
        return null;
    }

    @Override
    public Response getRole(String id) {
        return null;
    }

    @Override
    public Response createRole(String name) {
        return null;
    }

    @Override
    public Response updateRole(String id, String name) {
        return null;
    }

    @Override
    public Response deleteRole(String id) {
        return null;
    }

    @Override
    public Response getRoleUsers(String id) {
        return null;
    }

    @Override
    public Response getRoleUser(String id, String userId) {
        return null;
    }
    private static CredentialRepresentation credentialRepresentation(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }
}

