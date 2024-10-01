package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.in.account.SendMoneyUseCase;
import dev.kauanmocelin.bank.application.port.out.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.port.out.persistence.CustomerRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class SendMoneyService implements SendMoneyUseCase {

    private final AccountRepository accountRepository;

    @Override
    public boolean sendMoney(Long sourceAccountId, Long targetAccountId, Double money) {

        Account sourceAccount = accountRepository.loadAccount(sourceAccountId);
        Account targetAccount = accountRepository.loadAccount(targetAccountId);

        sourceAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));
        targetAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected target account ID not to be empty"));

        if (!sourceAccount.withdraw(money)) return false;
        if (!targetAccount.deposit(money)) return false;

        accountRepository.updateAccount(sourceAccount);
        accountRepository.updateAccount(targetAccount);
        return true;
    }
}
