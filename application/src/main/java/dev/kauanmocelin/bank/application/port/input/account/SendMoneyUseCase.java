package dev.kauanmocelin.bank.application.port.input.account;

public interface SendMoneyUseCase {
    boolean sendMoney(final String sourceAccountId, final String targetAccountId, Double money);
}
