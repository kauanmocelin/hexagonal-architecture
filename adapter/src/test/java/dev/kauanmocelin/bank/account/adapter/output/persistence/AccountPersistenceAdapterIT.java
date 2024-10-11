package dev.kauanmocelin.bank.account.adapter.output.persistence;

import dev.kauanmocelin.bank.account.adapter.output.persistence.jpa.AccountPersistenceAdapter;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest
class AccountPersistenceAdapterIT {

    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse(
        "postgres:16-alpine"
    ));

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Autowired
    private AccountPersistenceAdapter accountPersistenceAdapter;

    @Test
    @Sql("AccountPersistenceAdapterTest.sql")
    void shouldRetrieveExistingAccountWithBalanceOfOneHundred() {
        Optional<Account> retrievedAccount = accountPersistenceAdapter.findBy(new AccountNumber("123456"));

        assertThat(retrievedAccount)
            .isPresent()
            .hasValueSatisfying(account -> assertThat(account.getBalance()).isEqualTo(100.00));
    }
}