package dev.kauanmocelin.bank.account.application.service;

import dev.kauanmocelin.bank.account.application.port.in.GetAccountBalanceQuery;
import dev.kauanmocelin.bank.account.application.port.out.LoadAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Double getAccountBalance(Long accountId) {
        return loadAccountPort.loadAccount(accountId)
                .getBalance();
    }
}
