package dev.kauanmocelin.bank.application.port.out.persistence;

import dev.kauanmocelin.bank.domain.customer.Customer;
import dev.kauanmocelin.bank.domain.customer.Email;

import java.util.Optional;

public interface CustomerRepository {
    Customer createCustomer(Customer customer);
    Optional<Customer> loadCustomer(Email email);
}
