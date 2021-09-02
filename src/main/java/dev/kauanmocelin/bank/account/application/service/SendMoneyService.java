package dev.kauanmocelin.bank.account.application.service;

import dev.kauanmocelin.bank.account.application.port.in.SendMoneyUseCase;
import dev.kauanmocelin.bank.account.application.port.out.LoadAccountPort;
import dev.kauanmocelin.bank.account.application.port.out.UpdateAccountPort;
import dev.kauanmocelin.bank.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountPort updateAccountPort;

    @Override
    public boolean sendMoney(Long sourceAccountId, Long targetAccountId, Double money) {

        Account sourceAccount = loadAccountPort.loadAccount(sourceAccountId);
        Account targetAccount = loadAccountPort.loadAccount(targetAccountId);

        sourceAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));
        targetAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected target account ID not to be empty"));

        if (!sourceAccount.withdraw(money)) return false;
        if (!targetAccount.deposit(money)) return false;

        updateAccountPort.updateAccount(sourceAccount);
        updateAccountPort.updateAccount(targetAccount);
        return true;
    }
}
