package dev.kauanmocelin.bank.account.adapter.output.persistence.jpa;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AccountMapper {

    Account mapToDomainEntity(AccountJpaEntity account) {
        //TODO: arrumar o new Account
        return new Account(new AccountNumber(account.getId()), account.getBalance(), new ArrayList<>());
    }

    public AccountJpaEntity mapToJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.getAccountNumber().orElse(null),
                account.getBalance());
    }
}
