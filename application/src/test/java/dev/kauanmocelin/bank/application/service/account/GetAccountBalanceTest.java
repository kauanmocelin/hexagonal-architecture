package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.service.account.exception.AccountNotExistsException;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAccountBalanceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private GetAccountBalance getAccountBalance;

    @Test
    void shouldSuccessfulReturnBalanceWhenGetBalanceFromExistingAccount() {
        final var account = new Account(new AccountNumber(1L), 250.00, List.of());
        when(accountRepository.findByAccountNumber(new AccountNumber(1L))).thenReturn(Optional.of(account));

        final var accountBalance = getAccountBalance.getAccountBalance(new AccountNumber(1L));

        assertThat(accountBalance).isEqualTo(250.00);
        verify(accountRepository).findByAccountNumber(any(AccountNumber.class));
    }

    @Test
    void shouldThrownAnExceptionWhenAccountNotExists() {
        when(accountRepository.findByAccountNumber(new AccountNumber(1L))).thenReturn(Optional.empty());

        assertThatExceptionOfType(AccountNotExistsException.class)
            .isThrownBy(() -> getAccountBalance.getAccountBalance(new AccountNumber(1L)))
            .withMessage("Account number 1 not exists");
        verify(accountRepository).findByAccountNumber(any(AccountNumber.class));
    }
}