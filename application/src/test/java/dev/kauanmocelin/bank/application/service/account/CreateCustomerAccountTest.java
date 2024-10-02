package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.input.account.exception.CustomerAlreadyExistsException;
import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.port.output.persistence.CustomerRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.customer.Customer;
import dev.kauanmocelin.bank.domain.customer.vo.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerAccountTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CreateCustomerAccount createCustomerAccount;

    @Test
    void shouldThrowsAnExceptionWhenCreateCustomerAccountWithEmailAlreadyExists() {
        Optional<Customer> existingCustomer = Optional.of(new Customer(
                new Email("johndoe@gmail.com"),
                "John Doe",
                Account.toSaveNew()));
        when(customerRepository.loadCustomer(new Email("johndoe@gmail.com"))).thenReturn(existingCustomer);

        assertThatExceptionOfType(CustomerAlreadyExistsException.class)
                .isThrownBy(() -> createCustomerAccount.createCustomerAccount(new Customer(new Email("johndoe@gmail.com"), "Jean Paul", Account.toSaveNew())))
                .withMessage("Customer already exists with email: johndoe@gmail.com");
        verify(customerRepository, never()).createCustomer(any());
    }
}