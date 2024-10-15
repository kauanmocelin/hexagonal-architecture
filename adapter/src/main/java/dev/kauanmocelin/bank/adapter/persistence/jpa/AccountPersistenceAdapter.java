package dev.kauanmocelin.bank.adapter.persistence.jpa;

import dev.kauanmocelin.bank.adapter.persistence.jpa.mapper.AccountMapper;
import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AccountPersistenceAdapter implements AccountRepository {

    private final SpringDataAccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account save(Account account) {
        throw new NotYetImplementedException();
    }

    @Override
    public Optional<Account> findBy(AccountNumber accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber.toLong()).map(accountMapper::toDomain);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(accountMapper.toEntity(account));
    }
}
