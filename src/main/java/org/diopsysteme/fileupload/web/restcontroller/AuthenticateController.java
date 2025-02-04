//package org.diopsysteme.fileupload.web.restcontroller;
//
//import org.diopsysteme.fileupload.domain.data.entities.User;
//import org.diopsysteme.fileupload.services.interfaces.AuthenticationIService;
//import org.diopsysteme.fileupload.services.mappers.LoginMapper;
//import org.diopsysteme.fileupload.model.dtos.requests.LoginRequestDto;
//import org.diopsysteme.fileupload.model.dtos.responses.LoginResponseDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import prog.dependancy.Services.interfaces.JwtServiceInterface;
//
//@RestController
//public class AuthenticateController {
//
//    private final JwtServiceInterface jwtServiceInterface;
//    @Autowired
//    private LoginMapper loginMapper;
//    private final AuthenticationIService authenticationIService;
//    public AuthenticateController(AuthenticationIService authenticationIService,JwtServiceInterface jwtServiceInterface){
//    this.authenticationIService=authenticationIService;
//    this.jwtServiceInterface=jwtServiceInterface;
//    }
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> auth(@RequestBody LoginRequestDto credentials){
//        User user = authenticationIService.authenticate(credentials);
//        System.out.println(user.toString());
//       LoginResponseDto loginResDto= new LoginResponseDto();
//       String token2 = jwtServiceInterface.generateToken(user);
//       loginResDto.setToken(token2);
//       System.out.println(loginResDto.toString());
//       return ResponseEntity.ok(loginResDto);
//
//    }
//}
