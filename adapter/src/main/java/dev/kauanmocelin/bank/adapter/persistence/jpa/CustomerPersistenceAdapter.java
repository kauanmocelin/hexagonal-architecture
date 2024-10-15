package dev.kauanmocelin.bank.adapter.persistence.jpa;

import dev.kauanmocelin.bank.application.port.output.persistence.CustomerRepository;
import dev.kauanmocelin.bank.domain.customer.Customer;
import dev.kauanmocelin.bank.domain.customer.vo.Email;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerPersistenceAdapter implements CustomerRepository {
    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> loadCustomer(Email email) {
        return Optional.empty();
    }
}
