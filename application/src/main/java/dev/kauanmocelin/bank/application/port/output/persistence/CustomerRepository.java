package dev.kauanmocelin.bank.application.port.output.persistence;

import dev.kauanmocelin.bank.domain.customer.Customer;
import dev.kauanmocelin.bank.domain.customer.vo.Email;

import java.util.Optional;

public interface CustomerRepository {
    Customer createCustomer(Customer customer);
    Optional<Customer> loadCustomer(Email email);
}
