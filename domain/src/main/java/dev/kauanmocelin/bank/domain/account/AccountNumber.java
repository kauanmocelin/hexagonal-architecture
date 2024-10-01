package dev.kauanmocelin.bank.domain.account;

public record AccountNumber(long value) {

    public AccountNumber {
        if(value < 1) {
            throw new IllegalArgumentException("'value' must be a positive long");
        }
    }
}
