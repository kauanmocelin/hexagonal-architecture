package dev.kauanmocelin.bank.account.adapter.out.persistence;

import dev.kauanmocelin.bank.account.application.port.out.LoadAccountPort;
import dev.kauanmocelin.bank.account.application.port.out.UpdateAccountPort;
import dev.kauanmocelin.bank.account.domain.Account;
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
