package com.ndourcodeur.companyservice.resource;

import com.ndourcodeur.companyservice.entities.User;
import com.ndourcodeur.companyservice.message.Message;
import com.ndourcodeur.companyservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(path = "/updateUser/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, @PathVariable Long id){
        User updatedUser = userService.editUser(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new Message("User successfully deleted with ID:"+id), HttpStatus.OK);
    }

    @GetMapping(path = "/findAllUsers")
    public ResponseEntity<?> findAllUsers(){
        List<User> users = userService.findAllUsers();
        if (users.isEmpty())
            return new ResponseEntity<>(new Message("No Content Here"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/findUserByUsername/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping(path = "/findUserById/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }
}
