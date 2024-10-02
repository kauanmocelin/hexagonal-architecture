package dev.kauanmocelin.bank.application.service.account;

import dev.kauanmocelin.bank.application.port.input.account.GetAccountBalanceQuery;
import dev.kauanmocelin.bank.application.port.output.persistence.AccountRepository;
import dev.kauanmocelin.bank.domain.account.Account;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final AccountRepository accountRepository;

    @Override
    public Double getAccountBalance(Long accountNumber) {
        return accountRepository.findByAccountNumber(new AccountNumber(accountNumber))
                .map(Account::getBalance)
                .orElse(0.0);
    }
}
