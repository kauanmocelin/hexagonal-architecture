package dev.kauanmocelin.bank.application.port.out.persistence;

import dev.kauanmocelin.bank.domain.account.Account;

public interface AccountRepository {
    Account createAccount(Account account);
    Account loadAccount(Long accountId);
    void updateAccount(Account account);
}
