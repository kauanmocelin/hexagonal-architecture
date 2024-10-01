package dev.kauanmocelin.bank.application.service.account.common;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.AccountNumber;

public class AccountCreator {

    public static Account createSourceAccountWithBalance(Double balance) {
        return Account.builder()
                .accountNumber(new AccountNumber(1L))
                .balance(balance)
                .build();
    }

    public static Account createTargetAccountWithBalance(Double balance) {
        return Account.builder()
                .accountNumber(new AccountNumber(42L))
                .balance(balance)
                .build();
    }
}
