package dev.kauanmocelin.bank.application.service.account.common;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;

import java.util.ArrayList;

public class AccountCreator {

    public static Account createSourceAccountWithBalance(Double balance) {
        return Account.builder()
                .accountNumber(new AccountNumber(1L))
                .balance(balance)
                .transactions(new ArrayList<>())
                .build();
    }

    public static Account createTargetAccountWithBalance(Double balance) {
        return Account.builder()
                .accountNumber(new AccountNumber(42L))
                .balance(balance)
                .transactions(new ArrayList<>())
                .build();
    }

    public static Account createAccount() {
        return Account.builder()
            .accountNumber(new AccountNumber(1L))
            .balance(100.00)
            .transactions(new ArrayList<>())
            .build();
    }
}
