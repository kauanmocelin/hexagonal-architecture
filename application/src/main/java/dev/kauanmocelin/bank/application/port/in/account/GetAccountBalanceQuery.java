package dev.kauanmocelin.bank.application.port.in.account;

public interface GetAccountBalanceQuery {
    Double getAccountBalance(Long accountId);
}
