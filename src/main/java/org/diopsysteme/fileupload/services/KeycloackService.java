package org.diopsysteme.fileupload.services;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.http.*;
import org.diopsysteme.fileupload.Web.Dtos.Requests.LoginReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.LoginResDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KeycloackService {
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri:http://localhost:8081/realms/master/protocol/openid-connect/token}")
    private String tokenUri;
    public ResponseEntity<TokenResponseDTO> genereToken(LoginReqDto loginReqDto){
       try{
           RestTemplate restTemplate = new RestTemplate();
           HttpHeaders httpHeaders = new HttpHeaders();
           httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
           MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
           body.add("client_id", clientId);
           body.add("grant_type", "password");
           body.add("username", loginReqDto.getLogin());
           body.add("password", loginReqDto.getPassword());
           HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, httpHeaders);
           ResponseEntity<TokenResponseDTO> response = restTemplate.exchange(
                   tokenUri,
                   HttpMethod.POST,
                   request,
                   TokenResponseDTO.class
           );
           System.out.println(response.getBody().toString());
           return response;
       }catch (Exception e){
           System.out.println(e.getMessage());
           throw new RuntimeException(e);
       }
    }




}