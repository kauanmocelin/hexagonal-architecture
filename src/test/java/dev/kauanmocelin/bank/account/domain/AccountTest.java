package dev.kauanmocelin.bank.account.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    @DisplayName("Should withdraw money from account when successful")
    void shouldWithdrawMoneyFromAccountWhenSuccessful() {
        Account account = Account.builder()
                .id(1L)
                .balance(100.00)
                .build();

        boolean withdrawResult = account.withdraw(50.00);

        assertThat(withdrawResult).isTrue();
        assertThat(account.getBalance()).isEqualTo(50.00);
    }

    @Test
    @DisplayName("Should not withdraw money from account when failure")
    void shouldNotWithdrawMoneyFromAccountWhenFailure() {
        Account account = Account.builder()
                .id(1L)
                .balance(100.00)
                .build();

        boolean withdrawResult = account.withdraw(150.00);

        assertThat(withdrawResult).isFalse();
        assertThat(account.getBalance()).isEqualTo(100.00);
    }

    @Test
    @DisplayName("Should deposit money to account when successful")
    void shouldDepositMoneyToAccountWhenSuccessful() {
        Account account = Account.builder()
                .id(1L)
                .balance(100.00)
                .build();

        boolean depositResult = account.deposit(100.00);

        assertThat(depositResult).isTrue();
        assertThat(account.getBalance()).isEqualTo(200.00);
    }
}