package org.diopsysteme.fileupload.repositories;

import org.diopsysteme.fileupload.domain.entities.User;
import prog.dependancy.Datas.Repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends SoftDeleteRepository<User, Long> {
    Optional<User> findByLogin(String login);

}
