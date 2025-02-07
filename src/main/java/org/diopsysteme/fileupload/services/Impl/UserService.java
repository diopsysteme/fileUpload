package org.diopsysteme.fileupload.services.Impl;

import org.diopsysteme.fileupload.domain.entities.User;
import org.diopsysteme.fileupload.repositories.UserRepository;
import org.diopsysteme.fileupload.services.mappers.UserMapper;
import org.diopsysteme.fileupload.model.dtos.requests.UserRequestDto;
import org.diopsysteme.fileupload.model.dtos.responses.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import prog.dependancy.Services.Interfaces.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserRequestDto, UserResponseDto> {
    @Autowired
    KeycloackAdminService keycloackAdminService;
    @Autowired
    private UserMapper mapper2;

private PasswordEncoder passwordEncoder;
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        mapper2=mapper;
        this.mapper = mapper;
    }
    @Override
   public UserResponseDto save(UserRequestDto userRequestDto){
        User user = mapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
       User user1 = repository.save(user);
         keycloackAdminService.createUser(mapper2.toKeycloak(user1));
        return mapper.toDto(user1);
    }
}
