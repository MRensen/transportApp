package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.User;
import com.MRensen.transportApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getByUsername(String username){
        return userRepository.getById(username);
    }

    public void updatePassword(String username, String password){
        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setPassword(password);
            userRepository.save(user);
        } else {
            throw new RecordNotFoundException("User not found");
        }
    }

    public void updatePhoto(String username, MultipartFile image) throws IOException {
        Optional<User> userOptional = userRepository.findById(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            byte[] decodedImg = image.getBytes();
//            String partSeparator = ",";
//            if (image.contains(partSeparator)) {
//                String encodedImg = image.split(partSeparator)[1];
//                byte[] decodedImg = Base64.getEncoder().encode(image.getBytes(StandardCharsets.UTF_8));
//                user.setImage(decodedImg);
                userRepository.save(user);
//            }
        }
    }
}
