package dev.kauanmocelin.bank.account.adapter.in.rest;

import dev.kauanmocelin.bank.account.adapter.in.rest.account.SendMoneyController;
import dev.kauanmocelin.bank.application.port.in.account.GetAccountBalanceQuery;
import dev.kauanmocelin.bank.application.port.in.account.SendMoneyUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.when;

@WebMvcTest(controllers = SendMoneyController.class)
class SendMoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SendMoneyUseCase sendMoneyUseCase;

    @MockBean
    GetAccountBalanceQuery getAccountBalanceQuery;

    @Test
    void sendMoney() throws Exception {
        final Long sourceAccountId = 1L;
        final Long targetAccountId = 42L;
        final Double amount = 500.00;

        mockMvc.perform(MockMvcRequestBuilders.post("/account/send/{sourceAccountId}/{targetAccountId}/{amount}",
                                sourceAccountId, targetAccountId, amount)
                        .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        when(sendMoneyUseCase.sendMoney(
                ArgumentMatchers.eq(sourceAccountId),
                ArgumentMatchers.eq(targetAccountId),
                ArgumentMatchers.eq(amount))
        ).thenReturn(true);
    }
}