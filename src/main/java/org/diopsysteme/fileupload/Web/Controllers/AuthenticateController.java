//package org.diopsysteme.fileupload.Web.Controllers;
//
//import org.diopsysteme.fileupload.Data.Entities.User;
//import org.diopsysteme.fileupload.services.Interfaces.AuthenticationIService;
//import org.diopsysteme.fileupload.Web.Dtos.Mappers.LoginMapper;
//import org.diopsysteme.fileupload.Web.Dtos.Requests.LoginReqDto;
//import org.diopsysteme.fileupload.Web.Dtos.Responses.LoginResDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import prog.dependancy.Services.Interfaces.JwtServiceInterface;
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
//    public ResponseEntity<LoginResDto> auth(@RequestBody LoginReqDto credentials){
//        User user = authenticationIService.authenticate(credentials);
//        System.out.println(user.toString());
//       LoginResDto loginResDto= new LoginResDto();
//       String token2 = jwtServiceInterface.generateToken(user);
//       loginResDto.setToken(token2);
//       System.out.println(loginResDto.toString());
//       return ResponseEntity.ok(loginResDto);
//
//    }
//}
