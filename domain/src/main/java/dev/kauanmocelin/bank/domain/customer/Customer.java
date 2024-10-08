package dev.kauanmocelin.bank.domain.customer;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.customer.vo.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Customer {

    private final Email email;
    private final String name;
    private final Account account;
}
