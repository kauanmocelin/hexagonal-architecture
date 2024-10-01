package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.in.account.CustomerAlreadyExistsException;
import dev.kauanmocelin.bank.application.port.out.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.port.out.persistence.CustomerRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.AccountNumber;
import dev.kauanmocelin.bank.domain.customer.Customer;
import dev.kauanmocelin.bank.domain.customer.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerAccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CreateCustomerAccountService createCustomerAccountService;

    @Test
    void shouldThrowsAnExceptionWhenCreateCustomerAccountWithEmailAlreadyExists() {
        Optional<Customer> existingCustomer = Optional.of(new Customer(
                new Email("johndoe@gmail.com"),
                "John Doe",
                Account.loadExist(new AccountNumber(1L), 0.0, List.of())));
        when(customerRepository.loadCustomer(new Email("johndoe@gmail.com"))).thenReturn(existingCustomer);

        assertThatExceptionOfType(CustomerAlreadyExistsException.class)
                .isThrownBy(() -> createCustomerAccountService.createCustomerAccount("Jean Paul", "johndoe@gmail.com"))
                .withMessage("Customer already exists with email: johndoe@gmail.com");
        verify(customerRepository, never()).createCustomer(any());
    }
}