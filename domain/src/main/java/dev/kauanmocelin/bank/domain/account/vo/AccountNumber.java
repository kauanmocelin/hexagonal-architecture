package dev.kauanmocelin.bank.domain.account.vo;

import java.util.Objects;
import java.util.Random;

public record AccountNumber(String value) {

    public AccountNumber {
        if(Objects.isNull(value) || value.isEmpty()) {
            throw new IllegalArgumentException("'value' must not be null nor empty");
        }
        if(value.length() > 8) {
            throw new IllegalArgumentException("'value' must has maximum 8 numbers");
        }
        if(Long.parseLong(value) < 1) {
            throw new IllegalArgumentException("'value' must be a positive number");
        }
    }

    /**
     * Generate a random identifier number with maximum 8 numbers for account number.
     * @return create an instance of {@link AccountNumber} value
     */
    public static AccountNumber generate() {
        return new AccountNumber(String.valueOf(Math.max(1, new Random().nextInt(100000000))));
    }
}
