package ru.sobse.identityservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sobse.identityservice.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
