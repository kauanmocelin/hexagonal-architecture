package dev.kauanmocelin.bank.account.application.port.out;

import dev.kauanmocelin.bank.account.domain.Account;

public interface LoadAccountPort {
    Account loadAccount(Long accountId);
}
