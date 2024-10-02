package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.service.account.common.AccountCreator;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendMoneyServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private SendMoneyService sendMoneyService;

    @Test
    void shouldTransferMoneyBetweenAccountsWhenSuccessful() {
        Account sourceAccount = mockSourceAccountWithBalance(100.00);
        Account targetAccount = mockTargetAccountWithBalance(200.00);

        boolean sendMoneyResult = sendMoneyService.sendMoney(
                sourceAccount.getAccountNumber().get(),
                targetAccount.getAccountNumber().get(),
                100.00);

        assertThat(sendMoneyResult).isTrue();
        assertThat(sourceAccount.getBalance()).isEqualTo(0.00);
        assertThat(targetAccount.getBalance()).isEqualTo(300.00);
        verify(accountRepository, times(2)).updateAccount(ArgumentMatchers.any(Account.class));
    }

    private Account mockSourceAccountWithBalance(double balance) {
        Account sourceAccount = AccountCreator.createSourceAccountWithBalance(balance);
        BDDMockito.when(accountRepository.findByAccountNumber(new AccountNumber(1L)))
                .thenReturn(Optional.of(sourceAccount));
        return sourceAccount;
    }

    private Account mockTargetAccountWithBalance(double balance) {
        Account targetAccount = AccountCreator.createTargetAccountWithBalance(balance);
        BDDMockito.when(accountRepository.findByAccountNumber(new AccountNumber(42L)))
                .thenReturn(Optional.of(targetAccount));
        return targetAccount;
    }
}