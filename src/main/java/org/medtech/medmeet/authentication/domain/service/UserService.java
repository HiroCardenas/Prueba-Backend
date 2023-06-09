package org.medtech.medmeet.authentication.domain.service;


import org.medtech.medmeet.authentication.domain.model.entity.User;
import org.medtech.medmeet.authentication.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto signIn(User user);
    void signUp(User user);
    List<User> getAll();
}
