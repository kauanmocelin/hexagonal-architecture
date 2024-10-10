package dev.kauanmocelin.bank.account.adapter.output.persistence.inmemory;

import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAccountRepository implements AccountRepository {

    private final Map<AccountNumber, Account> accounts = new ConcurrentHashMap<>();
    private long incrementalAccountNumber = 1;

    @Override
    public Account save(Account account) {
        AccountNumber nextAccountNumber = new AccountNumber(String.valueOf(incrementalAccountNumber++));
        Account newAccount = new Account(nextAccountNumber, account.getBalance(), account.getTransactions());
        accounts.put(nextAccountNumber, newAccount);
        return newAccount;
    }

    @Override
    public Optional<Account> findBy(AccountNumber accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    @Override
    public void updateAccount(Account account) {

    }
}
