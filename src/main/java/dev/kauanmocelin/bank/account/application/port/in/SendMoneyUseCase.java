package dev.kauanmocelin.bank.account.application.port.in;

public interface SendMoneyUseCase {
    boolean sendMoney(Long sourceAccountId, Long targetAccountId, Double money);
}
