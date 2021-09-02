package dev.kauanmocelin.bank.account.adapter.in.web;

import dev.kauanmocelin.bank.account.application.port.in.GetAccountBalanceQuery;
import dev.kauanmocelin.bank.account.application.port.in.SendMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "account")
@RequiredArgsConstructor
public class SendMoneyController {

    private final GetAccountBalanceQuery getAccountBalanceQuery;
    private final SendMoneyUseCase sendMoneyUseCase;

    @GetMapping(path = "/{accountId}")
    public Double getAccountBalance(@PathVariable Long accountId) {
        return getAccountBalanceQuery.getAccountBalance(accountId);
    }

    @PostMapping(path = "/send/{sourceAccountId}/{targetAccountId}/{amount}")
    public Boolean sendMoney(
            @PathVariable Long sourceAccountId,
            @PathVariable Long targetAccountId,
            @PathVariable Double amount) {

        return sendMoneyUseCase.sendMoney(sourceAccountId, targetAccountId, amount);
    }
}
