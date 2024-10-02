package dev.kauanmocelin.bank.domain.account;

import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import dev.kauanmocelin.bank.domain.transaction.Transaction;
import dev.kauanmocelin.bank.domain.transaction.TransactionType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
public class Account {

    private AccountNumber accountNumber;
    private Double balance;
    private final List<Transaction> transactions;

    public Account(AccountNumber accountNumber, Double balance, List<Transaction> transactions) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = transactions;
    }

    public static Account toSave(){
        return new Account(null, 0.0, List.of());
    }

    public static Account loadExist(AccountNumber id, Double balance, List<Transaction> transactions) {
        return new Account(id, balance, transactions);
    }

    public Optional<Long> getAccountNumber() {
        return Optional.of(this.accountNumber.value());
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
