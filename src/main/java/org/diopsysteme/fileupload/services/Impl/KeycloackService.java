package org.diopsysteme.fileupload.services.Impl;

import org.diopsysteme.fileupload.model.dtos.requests.LoginRequestDto;
import org.springframework.http.*;
import org.diopsysteme.fileupload.model.dtos.responses.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloackService {
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri:http://localhost:8081/realms/master/protocol/openid-connect/token}")
    private String tokenUri;
    public ResponseEntity<TokenResponseDTO> genereToken(LoginRequestDto loginRequestDto){
       try{
           RestTemplate restTemplate = new RestTemplate();
           HttpHeaders httpHeaders = new HttpHeaders();
           httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
           MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
           body.add("client_id", clientId);
           body.add("grant_type", "password");
           body.add("username", loginRequestDto.getLogin());
           body.add("password", loginRequestDto.getPassword());
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