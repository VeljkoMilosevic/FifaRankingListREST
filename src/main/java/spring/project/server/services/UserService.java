/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.exceptions.UserNotFoundException;
import spring.project.server.model.User;
import spring.project.server.repositories.UserRepository;

import java.util.List;

/**
 * @author Veljko
 */
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void setUserRepository(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(final int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(final int id) {
        userRepository.deleteById(id);
    }

    public void saveUser(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(final int id, final User updatedUser) {
        final User user = getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " does not exists.");
        }
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setActive(updatedUser.isActive());
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User login(final User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User findByUsername(final User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    public void updateUsers(final List<User> users) {
        users.forEach(user -> userRepository.save(user));
    }

}
