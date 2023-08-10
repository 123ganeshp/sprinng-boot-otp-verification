package com.example.registerapp.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");
        mimeMessageHelper.setText("""
                    <div class="container">
                        <h1>Hoonartek</h1>
                        <p>Dear Consumer,</p>
                        <p>Please use the following One Time Password (OTP) to complete the procedure. OTP is valid for 5 minutes.</p>
                        <h2 style="background-color: #f2f2f2; padding: 10px; border: 1px solid #ccc;">%s</h2>
                        <p>Thank You</p>
                        </div>
                """.formatted(otp), true);

        javaMailSender.send(mimeMessage);
    }
}
