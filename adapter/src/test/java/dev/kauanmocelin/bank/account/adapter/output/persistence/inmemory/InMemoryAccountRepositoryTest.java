package dev.kauanmocelin.bank.account.adapter.output.persistence.inmemory;

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

        Optional<Account> account = accountRepository.findByAccountNumber(new AccountNumber(1L));

        assertThat(account).isEmpty();
    }
}