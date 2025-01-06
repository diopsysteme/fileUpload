package org.diopsysteme.fileupload.Web.Controllers;

import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Web.Dtos.Requests.UserReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.UserResDto;
import prog.dependancy.Web.Controller.AbstractController;
import prog.dependancy.Services.Interfaces.AbstractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<User, UserReqDto, UserResDto> {
    public UserController(AbstractService<User, UserReqDto, UserResDto> service) {
        super(service);
    }
}
