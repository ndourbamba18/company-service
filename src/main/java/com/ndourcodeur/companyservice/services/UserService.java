package com.ndourcodeur.companyservice.services;

import com.ndourcodeur.companyservice.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

   // User addUser(User user);

    User editUser(User user, Long idUser);

    void deleteUser(Long idUser);

    List<User> findAllUsers();

    User findUserById(Long idUser);

    User findByUsername(String username);
}
