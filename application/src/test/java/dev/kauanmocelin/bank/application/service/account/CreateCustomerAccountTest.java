package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.port.output.persistence.CustomerRepository;
import dev.kauanmocelin.bank.application.service.account.common.AccountCreator;
import dev.kauanmocelin.bank.application.service.account.common.CustomerCreator;
import dev.kauanmocelin.bank.application.service.account.exception.CustomerAlreadyExistsException;
import dev.kauanmocelin.bank.domain.customer.Customer;
import dev.kauanmocelin.bank.domain.customer.vo.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void shouldThrowsAnExceptionWhenEmailAlreadyExists() {
        when(customerRepository.loadCustomer(new Email("johndoe@gmail.com"))).thenReturn(Optional.of(CustomerCreator.createCustomerWithoutAccount()));

        assertThatExceptionOfType(CustomerAlreadyExistsException.class)
                .isThrownBy(() -> createCustomerAccount.createCustomerAccount(CustomerCreator.createCustomerWithoutAccount()))
                .withMessage("Customer already exists with email: johndoe@gmail.com");
        verify(customerRepository).loadCustomer(any());
        verify(customerRepository, never()).createCustomer(any());
        verify(accountRepository, never()).save(any());
    }

    @Test
    void shouldCreateNewCustomerWhenAllFieldsAreValid() {
        final var customerWithoutAccount = CustomerCreator.createCustomerWithoutAccount();
        final var customerWithAccount = CustomerCreator.createCustomerWithAccount();

        when(customerRepository.loadCustomer(customerWithoutAccount.getEmail())).thenReturn(Optional.empty());
        when(accountRepository.save(any())).thenReturn(AccountCreator.createAccount());
        when(customerRepository.createCustomer(any())).thenReturn(customerWithAccount);

        Customer customerCreated = createCustomerAccount.createCustomerAccount(customerWithoutAccount);

        assertThat(customerCreated).isSameAs(customerWithAccount);
        verify(customerRepository).loadCustomer(any());
        verify(customerRepository).createCustomer(any());
        verify(accountRepository).save(any());
    }
}