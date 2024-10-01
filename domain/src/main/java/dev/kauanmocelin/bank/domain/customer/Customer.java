package dev.kauanmocelin.bank.domain.customer;

import dev.kauanmocelin.bank.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {

    private final Email email;
    private final String name;
    private final Account account;
}
