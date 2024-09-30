package dev.kauanmocelin.bank.application.port.out.persistence;

import dev.kauanmocelin.bank.domain.account.Account;

public interface LoadAccountPort {
    Account loadAccount(Long accountId);
}
