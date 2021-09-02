package dev.kauanmocelin.bank.common;

import dev.kauanmocelin.bank.account.domain.Account;

public class AccountCreator {

    public static Account createSourceAccountWithBalance(Double balance) {
        return Account.builder()
                .id(1L)
                .balance(balance)
                .build();
    }

    public static Account createTargetAccountWithBalance(Double balance) {
        return Account.builder()
                .id(42L)
                .balance(balance)
                .build();
    }
}
