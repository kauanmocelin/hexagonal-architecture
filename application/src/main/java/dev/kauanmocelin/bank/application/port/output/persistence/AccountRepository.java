package dev.kauanmocelin.bank.application.port.output.persistence;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findBy(AccountNumber accountNumber);
    void updateAccount(Account account);
}
