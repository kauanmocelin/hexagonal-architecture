package dev.kauanmocelin.bank.application.port.input.account;

public interface GetAccountBalanceQuery {
    Double getAccountBalance(Long accountId);
}
