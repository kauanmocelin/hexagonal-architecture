package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.input.account.SendMoneyUseCase;
import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class SendMoney implements SendMoneyUseCase {

    private final AccountRepository accountRepository;

    @Override
    public boolean sendMoney(final String sourceAccountId, final String targetAccountId, Double money) {

        Optional<Account> sourceAccount = accountRepository.findByAccountNumber(new AccountNumber(sourceAccountId));
        Optional<Account> targetAccount = accountRepository.findByAccountNumber(new AccountNumber(targetAccountId));

        //TODO: verificar e corrigir os Optional
        sourceAccount.get().getAccountNumber()
                .orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));
        targetAccount.get().getAccountNumber()
                .orElseThrow(() -> new IllegalStateException("expected target account ID not to be empty"));

        if (!sourceAccount.get().withdraw(money)) return false;
        if (!targetAccount.get().deposit(money)) return false;

        accountRepository.updateAccount(sourceAccount.get());
        accountRepository.updateAccount(targetAccount.get());
        return true;
    }
}
