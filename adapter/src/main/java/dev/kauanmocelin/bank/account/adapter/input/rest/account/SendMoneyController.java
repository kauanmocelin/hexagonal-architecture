package dev.kauanmocelin.bank.account.adapter.input.rest.account;

import dev.kauanmocelin.bank.application.port.input.account.GetAccountBalanceQuery;
import dev.kauanmocelin.bank.application.port.input.account.SendMoneyUseCase;
import dev.kauanmocelin.bank.domain.account.vo.AccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "account")
@RequiredArgsConstructor
public class SendMoneyController {

    private final GetAccountBalanceQuery getAccountBalanceQuery;
    private final SendMoneyUseCase sendMoneyUseCase;

    @GetMapping(path = "/{accountId}")
    public Double getAccountBalance(@PathVariable Long accountNumber) {
        return getAccountBalanceQuery.getAccountBalance(new AccountNumber(accountNumber));
    }

    @PostMapping(path = "/send/{sourceAccountId}/{targetAccountId}/{amount}")
    public Boolean sendMoney(
            @PathVariable Long sourceAccountId,
            @PathVariable Long targetAccountId,
            @PathVariable Double amount) {

        return sendMoneyUseCase.sendMoney(sourceAccountId, targetAccountId, amount);
    }
}
