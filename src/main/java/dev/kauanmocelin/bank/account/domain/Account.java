package dev.kauanmocelin.bank.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Builder
public class Account {

    private final Long id;

    @Getter
    private Double balance;

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
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
