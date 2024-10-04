package dev.kauanmocelin.bank.domain.customer.vo;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public Email {
        Objects.requireNonNull(value, "'email' must not be null");
        if(value.isEmpty()) {
            throw new IllegalArgumentException("'email' must not be empty");
        }
        if(!isValidEmail(value)) {
            throw new IllegalArgumentException("'email' must be valid");
        }
    }

    private boolean isValidEmail(final String value) {
        return EMAIL_PATTERN.matcher(value).matches();
    }
}
