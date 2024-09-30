package dev.kauanmocelin.bank.application.service.account.common;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.AccountId;

public class AccountCreator {

    public static Account createSourceAccountWithBalance(Double balance) {
        return Account.builder()
                .id(new AccountId(1L))
                .balance(balance)
                .build();
    }

    public static Account createTargetAccountWithBalance(Double balance) {
        return Account.builder()
                .id(new AccountId(42L))
                .balance(balance)
                .build();
    }
}
