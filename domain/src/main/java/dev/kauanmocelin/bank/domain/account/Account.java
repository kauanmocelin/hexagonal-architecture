package dev.kauanmocelin.bank.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Builder
@Getter
public class Account {

    private final AccountId id;
    private Double balance;

    public Optional<Long> getId() {
        return Optional.of(this.id.value());
    }

    public boolean withdraw(Double money) {
        if (!mayWithdraw(money)) {
            return false;
        }

        this.balance -= money;
        return true;
    }

    private boolean mayWithdraw(Double money) {
        return money <= this.balance;
    }

    public boolean deposit(Double money) {
        this.balance += money;
        return true;
    }
}
