package org.medtech.medmeet.authentication.domain.repository;

import org.medtech.medmeet.authentication.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
