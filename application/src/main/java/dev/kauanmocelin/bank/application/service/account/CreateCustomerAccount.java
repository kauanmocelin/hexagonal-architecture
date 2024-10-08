package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.input.account.CreateCustomerAccountUseCase;
import dev.kauanmocelin.bank.application.service.account.exception.CustomerAlreadyExistsException;
import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.port.output.persistence.CustomerRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class CreateCustomerAccount implements CreateCustomerAccountUseCase {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomerAccount(final Customer customer) {
        customerRepository.loadCustomer(customer.getEmail())
            .ifPresent(existingClient -> {
                throw new CustomerAlreadyExistsException("Customer already exists with email: " + existingClient.getEmail().value());
            });
        Account newAccount = accountRepository.save(Account.toSave());
        return customerRepository.createCustomer(new Customer(customer.getEmail(), customer.getName(), newAccount));
    }
}
