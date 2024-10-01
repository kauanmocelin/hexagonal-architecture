package dev.kauanmocelin.bank.domain.customer;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Email(String value) {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public Email {
        Objects.requireNonNull(value, "'email' must not be null");
        if(value.isEmpty()) {
            throw new IllegalArgumentException("'email' must not be empty");
        }
        if(!validate(value)) {
            throw new IllegalArgumentException("'email' must be valid");
        }
    }

    private boolean validate(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
