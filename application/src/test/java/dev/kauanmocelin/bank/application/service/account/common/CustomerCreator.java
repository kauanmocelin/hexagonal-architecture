package dev.kauanmocelin.bank.application.service.account.common;

import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import dev.kauanmocelin.bank.domain.customer.Customer;
import dev.kauanmocelin.bank.domain.customer.vo.Email;

import java.util.ArrayList;

public class CustomerCreator {

    public static Customer createCustomerWithAccount() {
        return Customer.builder()
            .email(new Email("johndoe@gmail.com"))
            .name("John Doe")
            .account(Account.builder()
                .accountNumber(new AccountNumber(1L))
                .balance(100.00)
                .transactions(new ArrayList<>())
                .build())
            .build();
    }

    public static Customer createCustomerWithoutAccount() {
        return Customer.builder()
            .email(new Email("johndoe@gmail.com"))
            .name("John Doe")
            .account(Account.toSave())
            .build();
    }
}
