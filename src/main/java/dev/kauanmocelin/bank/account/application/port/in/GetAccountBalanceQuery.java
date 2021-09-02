package dev.kauanmocelin.bank.account.application.port.in;

public interface GetAccountBalanceQuery {
    Double getAccountBalance(Long accountId);
}
