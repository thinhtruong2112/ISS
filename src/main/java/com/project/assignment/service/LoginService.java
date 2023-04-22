package com.project.assignment.service;

import com.project.assignment.entity.UserEntity;
import com.project.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
//    @Value("${spring.datasource.username}")
//    private String dbUsername;
//    @Value("${spring.datasource.password}")
//    private String dbPassword;



    public Boolean login(String username, String password) {
//        UserEntity user = userRepository.findByUsernameAndPassword(username, password);
//        if (user == null) {
//            return false;
//        } else {
//            System.out.println(dbUsername);
//            System.out.println(dbPassword);
//            dbUsername = "author_1";
//            dbPassword = "author_1";
//            return true;
//        }
        return true;
    }
}
