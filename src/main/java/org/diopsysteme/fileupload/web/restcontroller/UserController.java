package org.diopsysteme.fileupload.web.restcontroller;

import org.diopsysteme.fileupload.domain.data.entities.User;
import org.diopsysteme.fileupload.model.dtos.requests.UserRequestDto;
import org.diopsysteme.fileupload.model.dtos.responses.UserResponseDto;
import prog.dependancy.Web.Controller.AbstractController;
import prog.dependancy.Services.Interfaces.AbstractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<User, UserRequestDto, UserResponseDto> {
    public UserController(AbstractService<User, UserRequestDto, UserResponseDto> service) {
        super(service);
    }
}
