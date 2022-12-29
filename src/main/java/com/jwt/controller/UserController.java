package com.jwt.controller;

import com.jwt.entity.AuthEntity;
import com.jwt.entity.User;
import com.jwt.reposetory.UserRepo;
import com.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String fun(){
        return "welcome to the world to security !!";
    }


    @PostMapping("/login")
    public String genrateToken(@RequestBody AuthEntity authEntity) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authEntity.getUserName(),authEntity.getPassword()
                    ));
        }catch (Exception e){
            throw new Exception("Username or password is wrong");
        }
        return jwtUtil.generateToken(authEntity.getUserName());
    }
    @GetMapping("/getUser")
    public ResponseEntity<List<User>> getUser(){
        List<User> user=  this.userRepo.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
