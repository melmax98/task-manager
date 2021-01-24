package org.example.service;

import org.example.entity.Unit;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setUsername(user.getUsername());
        user.setRoles(Collections.singleton(Unit.DEVELOPER));
        return userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void updateUser(Integer id, User updated) {
        User user = userRepository.findById(id).get();
        if (updated.getRoles() != null) {
            user.setRoles(updated.getRoles());
        }

        if (updated.getUsername() != null) {
            user.setUsername(updated.getUsername());
        }
        userRepository.save(user);
    }
}
