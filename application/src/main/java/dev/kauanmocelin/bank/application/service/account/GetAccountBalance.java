package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.input.account.GetAccountBalanceQuery;
import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.application.service.account.exception.AccountNotExistsException;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountBalance implements GetAccountBalanceQuery {

    private final AccountRepository accountRepository;

    @Override
    public Double getAccountBalance(AccountNumber accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
            .map(Account::getBalance)
            .orElseThrow(() -> new AccountNotExistsException("Account number %d not exists".formatted(accountNumber.value())));
    }
}
