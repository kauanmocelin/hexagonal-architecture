package dev.kauanmocelin.bank.account.adapter.out.persistence.jpa;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.AccountNumber;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    Account mapToDomainEntity(AccountJpaEntity account) {
        return new Account(new AccountNumber(account.getId()), account.getBalance());
    }

    public AccountJpaEntity mapToJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.getAccountNumber().orElse(null),
                account.getBalance());
    }
}
