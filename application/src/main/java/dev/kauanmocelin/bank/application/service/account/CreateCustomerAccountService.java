package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.in.account.CreateCustomerAccountUseCase;
import dev.kauanmocelin.bank.application.port.in.account.CustomerAlreadyExistsException;
import dev.kauanmocelin.bank.application.port.out.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.port.out.persistence.CustomerRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.customer.Customer;
import dev.kauanmocelin.bank.domain.customer.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Objects;

@Transactional
@Component
@AllArgsConstructor
public class CreateCustomerAccountService implements CreateCustomerAccountUseCase {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public String createCustomerAccount(String name, String email) {
        Objects.requireNonNull(name, "'name' must not be null");
        Objects.requireNonNull(email, "'email' must not be null");
        customerRepository.loadCustomer(new Email(email))
            .ifPresent(customer -> {
                throw new CustomerAlreadyExistsException("Customer already exists with email: " + email);
            });
        Account newAccount = accountRepository.createAccount(Account.toSaveNew());
        Customer newCustomer = customerRepository.createCustomer(new Customer(new Email(email), name, newAccount));
        return newCustomer.getEmail().value();
    }
}
