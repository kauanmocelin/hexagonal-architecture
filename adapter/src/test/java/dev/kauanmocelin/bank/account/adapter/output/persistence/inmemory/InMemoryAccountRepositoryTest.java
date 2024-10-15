package dev.kauanmocelin.bank.account.adapter.output.persistence.inmemory;

import dev.kauanmocelin.bank.adapter.persistence.inmemory.InMemoryAccountRepository;
import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class InMemoryAccountRepositoryTest {

    @Test
    void shouldNotReturnAccountWhenAccountNotFoundWithAccountNumber() {
        AccountRepository accountRepository = new InMemoryAccountRepository();

        Optional<Account> account = accountRepository.findBy(new AccountNumber("54985623"));

        assertThat(account).isEmpty();
    }

    @Test
    void shouldReturnAccountWithBalanceZeroWhenNewAccountWasCreated() {
        AccountRepository accountRepository = new InMemoryAccountRepository();

        accountRepository.save(Account.toSave());
        Optional<Account> retrievedAccount = accountRepository.findBy(new AccountNumber("12345678"));

        assertThat(retrievedAccount)
            .isPresent()
            .hasValueSatisfying(account -> assertThat(account.getBalance()).isEqualTo(0.0));
    }
}