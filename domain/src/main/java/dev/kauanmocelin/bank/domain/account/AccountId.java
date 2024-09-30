package dev.kauanmocelin.bank.domain.account;

import java.util.Objects;

public record AccountId(long value) {

    public AccountId {
        if(value < 1) {
            throw new IllegalArgumentException("'value' must be a positive long");
        }
    }
}
