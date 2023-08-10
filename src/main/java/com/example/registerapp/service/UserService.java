package com.example.registerapp.service;

import com.example.registerapp.dto.RegisterDto;
import com.example.registerapp.entity.User;
import com.example.registerapp.repository.UserRepository;
import com.example.registerapp.util.EmailUtil;
import com.example.registerapp.util.OtpUtil;
import com.example.registerapp.util.ValidationUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;

    public static final int EXPIRES_IN = 1 * 300;

    public String register(RegisterDto user) {
        User user1 = new User();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setMobileNumber(user.getMobileNumber());
        userRepository.save(user1);
        return "User registration successful";
    }

    public String verifyAccount(String contact, String otp) {
        if (ValidationUtil.isValidEmail(contact)) {
            User user = userRepository.findByEmail(contact)
                    .orElseThrow(() -> new RuntimeException("User not found with this email: " + contact));
            if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                    LocalDateTime.now()).getSeconds() < (EXPIRES_IN)) {
                userRepository.save(user);
                return "OTP verified Successfully";
            }
        } else if (ValidationUtil.isValidMobileNumber(contact)) {
            User user = userRepository.findByMobileNumber(contact)
                    .orElseThrow(() -> new RuntimeException("User not found with this mobile number: " + contact));
            if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                    LocalDateTime.now()).getSeconds() < (EXPIRES_IN)) {
                userRepository.save(user);
                return "OTP verified Successfully";
            }
        }
        return "Please regenerate otp and try again";
    }

    public String sendOTPByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent... please verify account within 1 minute";
    }

    public String sendOTPByMobileNumber(String mobileNumber) {
        return "It requires money";
    }

    public String generateOtp(String contact) {
        System.out.println("Service : " + contact);
        if (ValidationUtil.isValidEmail(contact)) {
            // Send email
            System.out.println("Valid email : " + contact);
            return sendOTPByEmail(contact);
        } else if (ValidationUtil.isValidMobileNumber(contact)) {
            // Send mobile number message
            System.out.println("Valid mobile : " + contact);
            return sendOTPByMobileNumber(contact);
        } else {
            return "Invalid contact information.";
        }
    }

}
