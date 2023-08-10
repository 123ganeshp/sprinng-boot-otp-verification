package com.example.registerapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_NUMBER_REGEX = "^(?:(?:\\+|00)([1-9]\\d{0,2}))?[-.\\s]?\\(?([1-9]\\d{0,2})\\)?[-.\\s]?([1-9]\\d{2,3})[-.\\s]?([1-9]\\d{2,4})$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidMobileNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
