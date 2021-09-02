package dev.kauanmocelin.bank.account.adapter.out.persistence;

import dev.kauanmocelin.bank.account.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    Account mapToDomainEntity(AccountJpaEntity account) {
        return new Account(account.getId(), account.getBalance());
    }

    public AccountJpaEntity mapToJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.getId().orElse(null),
                account.getBalance());
    }
}
