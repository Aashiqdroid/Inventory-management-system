package com.aashiq.service;

import com.aashiq.entity.User;
import com.aashiq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //HASHING USER PASSWORD
    public String hashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest=
                MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] resultByteArray = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b: resultByteArray){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }

    public ResponseEntity<?> saveUser(@RequestBody User user) throws NoSuchAlgorithmException {
        String tempUsername = user.getUsername();
        if (tempUsername!=null){
            User user1 = userRepository.findByUsername(tempUsername);
            if (user1!=null){
                return (ResponseEntity<?>) ResponseEntity.internalServerError();
            }
        }
        user.setPassword(hashedPassword(user.getPassword()));


        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
    public List<User> showUsers(){
        return userRepository.findAll();
    }

    public User showByID(Long id) {
        return userRepository.findById(id).get();
    }



    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public String loginProcess(String username, String password) throws NoSuchAlgorithmException {
        User existingUser= userRepository.findByUsername(username);




        if (existingUser!=null){

            if (password.equals(existingUser.getPassword())){
                return "Login successful";
            }
        }
        return "Username and password are not valid!";

    }

    public User findByUsernameAndPassword(String tempUsername, String tempPassword) throws NoSuchAlgorithmException {
        tempPassword=hashedPassword(tempPassword);

        return userRepository.findByUsernameAndPassword(tempUsername,tempPassword);
    }

    public void updateItem(Long id, User user) {
        User updatedItem = userRepository.findById(id).get();
        updatedItem.setUsername(user.getUsername());
        updatedItem.setMobile(user.getMobile());
        updatedItem.setAddress(user.getAddress());
        userRepository.save(updatedItem);
    }
    public void updateUserByUsername(String username, User user) {
        User updatedItem = userRepository.findByUsername(username);
        updatedItem.setMobile(user.getMobile());
        updatedItem.setAddress(user.getAddress());
        updatedItem.setDob(user.getDob());
        userRepository.save(updatedItem);
    }
}
