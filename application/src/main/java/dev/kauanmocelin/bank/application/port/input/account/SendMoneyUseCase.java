package dev.kauanmocelin.bank.application.port.input.account;

public interface SendMoneyUseCase {
    boolean sendMoney(Long sourceAccountId, Long targetAccountId, Double money);
}
