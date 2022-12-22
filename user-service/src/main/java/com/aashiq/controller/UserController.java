package com.aashiq.controller;


import com.aashiq.config.MessageConfig;
import com.aashiq.entity.User;
import com.aashiq.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RabbitTemplate template;


    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws NoSuchAlgorithmException {
        userService.saveUser(user);
        template.convertAndSend(MessageConfig.EXCHANGE,
                MessageConfig.ROUTING_KEY,user);

        return ResponseEntity.ok(user) ;
    }


    @GetMapping("")
    public List<User> showUser(){
        return userService.showUsers();
    }


    @GetMapping("{username}/{password}")
    public String lettingTheUserLogin(@PathVariable String username, @PathVariable String password) throws NoSuchAlgorithmException {
        return userService.loginProcess(username,password);
    }

    @PostMapping("login")
    public ResponseEntity<?> userLogin(@RequestBody User user) throws NoSuchAlgorithmException {
        String tempUsername = user.getUsername();
        String tempPassword = user.getPassword();

        if (tempUsername != null && tempPassword != null){
            User existUser = userService.findByUsernameAndPassword(tempUsername,tempPassword);
            if (existUser!=null){
                return ok(user);
            }
        }
        return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }

    //UPDATE USER
    @PutMapping("{id}")
    public String updateItem(@PathVariable Long id, @RequestBody User user) {
        userService.updateItem(id, user);
        return "User updated";
    }
    @PutMapping("username/{username}")
    public ResponseEntity<User> updateItemByUsername(@PathVariable String username, @RequestBody User user) {
        userService.updateUserByUsername(username, user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("{username}")
    public User userBYuserName(@PathVariable String username){
        return userService.findByUsername(username);
    }
}
