package dev.kauanmocelin.bank.domain.account.vo;

public record AccountNumber(Long value) {

    public AccountNumber {
        if(value < 1) {
            throw new IllegalArgumentException("'value' must be a positive long");
        }
    }
}
