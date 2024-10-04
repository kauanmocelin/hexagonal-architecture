package dev.kauanmocelin.bank.domain.account.vo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AccountNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {"1","99999999", "95745235"})
    void shouldBeValidAccountNumberWhenValueIsBetweenEightNumbersAndIsPositive(String value) {
        AccountNumber accountNumber = new AccountNumber(value);

        assertThat(accountNumber).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-9999999","-100","-1", "0"})
    void shouldThrownAnExceptionWhenValueIsNotPositiveNumber(String value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new AccountNumber(value))
            .withMessage("'value' must be a positive number");
    }
}