package dev.kauanmocelin.bank.account.adapter.output.persistence.jpa;

import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements AccountRepository {

    private final SpringDataAccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account save(Account account) {
        throw new NotYetImplementedException();
    }

    @Override
    public Optional<Account> findByAccountNumber(AccountNumber accountNumber) {
        AccountJpaEntity account = accountRepository.findById(accountNumber.value())
                .orElseThrow(EntityNotFoundException::new);
        return Optional.of(accountMapper.mapToDomainEntity(account));
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(accountMapper.mapToJpaEntity(account));
    }
}
