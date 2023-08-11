package com.example.registerapp.controller;

import com.example.registerapp.dto.RegisterDto;
import com.example.registerapp.entity.CustomeResponse;
import com.example.registerapp.entity.User;
import com.example.registerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> register(@RequestBody RegisterDto user) {
        return new ResponseEntity<>(new CustomeResponse(userService.register(user)), HttpStatus.OK);
    }




    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam String contact,
                                                @RequestParam String otp) {
        return new ResponseEntity<>(new CustomeResponse(userService.verifyAccount(contact, otp)), HttpStatus.OK);
    }

    @PutMapping("/otp")
    public ResponseEntity<?> regenerateOtp(@RequestParam String contact) {
        System.out.println("Controller : "+contact);
        return new ResponseEntity<>(new CustomeResponse(userService.generateOtp(contact.trim())), HttpStatus.OK);
    }


//    @PutMapping("/regenerate-otp")
//    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
//        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
//    }
}
