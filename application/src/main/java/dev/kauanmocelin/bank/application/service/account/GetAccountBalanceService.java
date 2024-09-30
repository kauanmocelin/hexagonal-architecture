package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.in.account.GetAccountBalanceQuery;
import dev.kauanmocelin.bank.application.port.out.persistence.LoadAccountPort;
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