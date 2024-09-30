package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.out.persistence.LoadAccountPort;
import dev.kauanmocelin.bank.application.port.out.persistence.UpdateAccountPort;
import dev.kauanmocelin.bank.application.service.account.common.AccountCreator;
import dev.kauanmocelin.bank.domain.account.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendMoneyServiceTest {

    @Mock
    private LoadAccountPort loadAccountPort;
    @Mock
    private UpdateAccountPort updateAccountPort;
    @InjectMocks
    private SendMoneyService sendMoneyService;

    @Test
    void shouldTransferMoneyBetweenAccountsWhenSuccessful() {
        Account sourceAccount = mockSourceAccountWithBalance(100.00);
        Account targetAccount = mockTargetAccountWithBalance(200.00);

        boolean sendMoneyResult = sendMoneyService.sendMoney(
                sourceAccount.getId().get(),
                targetAccount.getId().get(),
                100.00);

        assertThat(sendMoneyResult).isTrue();
        assertThat(sourceAccount.getBalance()).isEqualTo(0.00);
        assertThat(targetAccount.getBalance()).isEqualTo(300.00);
        verify(updateAccountPort, times(2)).updateAccount(ArgumentMatchers.any(Account.class));
    }

    private Account mockSourceAccountWithBalance(double balance) {
        Account sourceAccount = AccountCreator.createSourceAccountWithBalance(balance);
        BDDMockito.when(loadAccountPort.loadAccount(1L))
                .thenReturn(sourceAccount);
        return sourceAccount;
    }

    private Account mockTargetAccountWithBalance(double balance) {
        Account targetAccount = AccountCreator.createTargetAccountWithBalance(balance);
        BDDMockito.when(loadAccountPort.loadAccount(42L))
                .thenReturn(targetAccount);
        return targetAccount;
    }
}