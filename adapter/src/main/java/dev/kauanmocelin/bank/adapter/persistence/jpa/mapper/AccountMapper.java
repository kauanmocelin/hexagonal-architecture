package dev.kauanmocelin.bank.adapter.persistence.jpa.mapper;

import dev.kauanmocelin.bank.adapter.persistence.jpa.AccountEntity;
import dev.kauanmocelin.bank.adapter.persistence.jpa.TransactionEntity;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import dev.kauanmocelin.bank.domain.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public Account toDomain(final AccountEntity account) {
        return Account.builder()
            .accountNumber(AccountNumber.from(String.valueOf(account.getAccountNumber())))
            .balance(account.getBalance())
            .transactions(new ArrayList<>())
            .build();
    }

    public AccountEntity toEntity(final Account account) {
        return AccountEntity.builder()
            .accountNumber(Long.parseLong(account.getAccountNumber().value()))
            .balance(account.getBalance())
            .transactions(toTransactionEntity(account.getTransactions()))
            .build();
    }

    private List<TransactionEntity> toTransactionEntity(final List<Transaction> transactions) {
        return transactions.stream()
            .map(transaction -> TransactionEntity.builder()
                .type(transaction.getType())
                .value(transaction.getValue())
                .dataHora(transaction.getDataHora())
                .build())
            .collect(Collectors.toList());
    }
}
