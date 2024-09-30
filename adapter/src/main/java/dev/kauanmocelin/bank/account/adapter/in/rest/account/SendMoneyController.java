package dev.kauanmocelin.bank.account.adapter.in.rest.account;

import dev.kauanmocelin.bank.application.port.in.account.GetAccountBalanceQuery;
import dev.kauanmocelin.bank.application.port.in.account.SendMoneyUseCase;
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
