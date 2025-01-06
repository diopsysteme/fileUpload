package org.diopsysteme.fileupload.Services.Impl;

import org.diopsysteme.fileupload.Data.Entities.User;
import org.diopsysteme.fileupload.Data.Repositories.UserRepository;
import org.diopsysteme.fileupload.Web.Dtos.Mappers.UserMapper;
import org.diopsysteme.fileupload.Web.Dtos.Requests.UserReqDto;
import org.diopsysteme.fileupload.Web.Dtos.Responses.UserResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import prog.dependancy.Services.Interfaces.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserReqDto, UserResDto> {
    @Autowired
private PasswordEncoder passwordEncoder;
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
   public UserResDto save(UserReqDto userReqDto){
        User user = mapper.toEntity(userReqDto);
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
       User user1 = repository.save(user);
        return mapper.toDto(user1);
    }
}
