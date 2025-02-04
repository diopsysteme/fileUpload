package org.diopsysteme.fileupload.services.Interfaces;
import org.diopsysteme.fileupload.model.dtos.requests.KeycloakRequestDto;
import org.keycloak.representations.idm.UserRepresentation;

import jakarta.ws.rs.core.Response;

public interface KeycloackAdminIService {

public Response createUser(UserRepresentation crUKcReqDTO);
public Response updateUser(Long id, KeycloakRequestDto keycloakRequestDto);
public Response deleteUser(Long id);
public UserRepresentation getUser(Long id);
public Response getUsers();
public Response getRoles();
public Response getRole(String id);
public Response createRole(String name);
public Response updateRole(String id, String name);
public Response deleteRole(String id);
public Response getRoleUsers(String id);
public Response getRoleUser(String id, String userId);



}
