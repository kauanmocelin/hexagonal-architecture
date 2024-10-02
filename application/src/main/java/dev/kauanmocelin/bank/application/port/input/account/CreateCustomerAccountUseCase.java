package dev.kauanmocelin.bank.application.port.input.account;

import dev.kauanmocelin.bank.domain.customer.Customer;

public interface CreateCustomerAccountUseCase {
    Customer createCustomerAccount(final Customer customer);
}
