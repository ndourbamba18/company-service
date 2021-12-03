package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.entities.User;
import com.ndourcodeur.companyservice.exception.ResourceNotFoundException;
import com.ndourcodeur.companyservice.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User editUser(User user, Long idUser) {
        User existsUser = userRepository.findById(idUser)
                .orElseThrow( () -> new ResourceNotFoundException("User not found with ID: "+idUser));
        existsUser.setUsername(user.getUsername());
        existsUser.setEmail(user.getEmail());
        existsUser.setPassword(user.getPassword());
        existsUser.setRoles(user.getRoles());
        return userRepository.save(existsUser);
    }

    @Override
    public void deleteUser(Long idUser) {
        User existsUser = userRepository.findById(idUser)
                .orElseThrow( () -> new ResourceNotFoundException("User not found with ID: "+idUser));
        userRepository.delete(existsUser);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findUserById(Long idUser) {
        User existsUser = userRepository.findById(idUser)
                .orElseThrow( () -> new ResourceNotFoundException("User not found with ID: "+idUser));
        return existsUser;
    }

    @Override
    public User findByUsername(String username) {
        User existsUser = userRepository.findByUsername(username)
                .orElseThrow( () -> new ResourceNotFoundException("User not found with USERNAME: "+username));
        return existsUser;
    }
}
