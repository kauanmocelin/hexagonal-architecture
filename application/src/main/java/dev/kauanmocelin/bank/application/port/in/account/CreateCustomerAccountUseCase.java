package dev.kauanmocelin.bank.application.port.in.account;

public interface CreateCustomerAccountUseCase {
    String createCustomerAccount(String name, String email);
}
