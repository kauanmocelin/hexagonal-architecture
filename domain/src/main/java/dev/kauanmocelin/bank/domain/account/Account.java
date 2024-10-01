package dev.kauanmocelin.bank.domain.account;

import dev.kauanmocelin.bank.domain.transaction.Transaction;
import dev.kauanmocelin.bank.domain.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
public class Account {

    private AccountId id;
    private Double balance;
    private final List<Transaction> transactions;

    private Account(AccountId id, Double balance, List<Transaction> transactions) {
        this.id = id;
        this.balance = balance;
        this.transactions = transactions;
    }

    public static Account toSaveNew(){
        return new Account(null, 0.0, List.of());
    }

    public static Account loadExist(AccountId id, Double balance, List<Transaction> transactions) {
        return new Account(id, balance, transactions);
    }

    public Optional<Long> getId() {
        return Optional.of(this.id.value());
    }

    public boolean withdraw(Double money) {
        if (!mayWithdraw(money)) {
            return false;
        }
        this.balance -= money;
        transactions.add(new Transaction(TransactionType.WITHDRAW, money));
        return true;
    }

    private boolean mayWithdraw(Double money) {
        return money <= this.balance;
    }

    public boolean deposit(Double money) {
        this.balance += money;
        transactions.add(new Transaction(TransactionType.DEPOSIT, money));
        return true;
    }
}
