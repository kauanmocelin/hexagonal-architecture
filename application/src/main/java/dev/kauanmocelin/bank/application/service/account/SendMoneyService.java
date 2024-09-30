package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.in.account.SendMoneyUseCase;
import dev.kauanmocelin.bank.application.port.out.persistence.LoadAccountPort;
import dev.kauanmocelin.bank.application.port.out.persistence.UpdateAccountPort;
import dev.kauanmocelin.bank.domain.account.Account;
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
