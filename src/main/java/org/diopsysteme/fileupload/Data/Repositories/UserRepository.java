package org.diopsysteme.fileupload.Data.Repositories;

import org.diopsysteme.fileupload.Data.Entities.User;
import prog.dependancy.Datas.Repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends SoftDeleteRepository<User, Long> {
    Optional<User> findByLogin(String login);

}
