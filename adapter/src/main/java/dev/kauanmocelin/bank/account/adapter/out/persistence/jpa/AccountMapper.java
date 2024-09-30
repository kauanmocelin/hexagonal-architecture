package dev.kauanmocelin.bank.account.adapter.out.persistence.jpa;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.AccountId;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    Account mapToDomainEntity(AccountJpaEntity account) {
        return new Account(new AccountId(account.getId()), account.getBalance());
    }

    public AccountJpaEntity mapToJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.getId().orElse(null),
                account.getBalance());
    }
}
