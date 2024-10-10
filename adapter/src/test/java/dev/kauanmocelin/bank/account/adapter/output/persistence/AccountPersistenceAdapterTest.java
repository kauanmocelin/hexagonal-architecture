package dev.kauanmocelin.bank.account.adapter.output.persistence;

import dev.kauanmocelin.bank.account.adapter.output.persistence.jpa.AccountPersistenceAdapter;
import dev.kauanmocelin.bank.account.adapter.output.persistence.jpa.mapper.AccountMapper;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({AccountPersistenceAdapter.class, AccountMapper.class})
class AccountPersistenceAdapterTest {

    @Autowired
    private AccountPersistenceAdapter accountPersistenceAdapter;

    @Test
    @Sql("AccountPersistenceAdapterTest.sql")
    void loadsAccount() {
        Optional<Account> account = accountPersistenceAdapter.findBy(new AccountNumber("123456"));

        assertThat(account.get().getBalance()).isEqualTo(100.00);
    }
}