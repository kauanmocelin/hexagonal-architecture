package dev.kauanmocelin.bank.application.port.in.account;

public interface SendMoneyUseCase {
    boolean sendMoney(Long sourceAccountId, Long targetAccountId, Double money);
}
