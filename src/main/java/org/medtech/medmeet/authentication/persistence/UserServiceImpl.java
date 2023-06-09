package org.medtech.medmeet.authentication.persistence;

import org.medtech.medmeet.authentication.domain.model.entity.User;
import org.medtech.medmeet.authentication.domain.repository.UserRepository;
import org.medtech.medmeet.authentication.domain.service.UserService;
import org.medtech.medmeet.authentication.dto.UserDto;
import org.medtech.medmeet.authentication.exception.AppException;
import org.medtech.medmeet.authentication.handler.JwtHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHandler jwtHandler;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHandler jwtHandler) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHandler = jwtHandler;
    }

    @Override
    public UserDto signIn(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            throw new AppException("User not found");
        }

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new AppException("Invalid password");
        }

        UserDto userDto = new UserDto();
        userDto.setId(existingUser.getId());
        userDto.setFirstName(existingUser.getFirstName());
        userDto.setLastName(existingUser.getLastName());
        userDto.setUsername(existingUser.getUsername());


        String token = jwtHandler.generateToken(user);
        userDto.setToken(token);

        return userDto;
    }


    @Override
    public void signUp(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new AppException("Username '" + user.getUsername() + "' is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

}

