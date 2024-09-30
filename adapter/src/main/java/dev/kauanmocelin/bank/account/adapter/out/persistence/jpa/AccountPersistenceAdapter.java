package dev.kauanmocelin.bank.account.adapter.out.persistence.jpa;

import dev.kauanmocelin.bank.application.port.out.persistence.LoadAccountPort;
import dev.kauanmocelin.bank.application.port.out.persistence.UpdateAccountPort;
import dev.kauanmocelin.bank.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountPort {

    private final SpringDataAccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(Long accountId) {
        AccountJpaEntity account = accountRepository.findById(accountId)
                .orElseThrow(EntityNotFoundException::new);

        return accountMapper.mapToDomainEntity(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(accountMapper.mapToJpaEntity(account));
    }
}
