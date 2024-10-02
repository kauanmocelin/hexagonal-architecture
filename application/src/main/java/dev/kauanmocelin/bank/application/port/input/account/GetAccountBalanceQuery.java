package dev.kauanmocelin.bank.application.port.input.account;

import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;

public interface GetAccountBalanceQuery {
    public Double getAccountBalance(AccountNumber accountNumber);
}
