package dev.kauanmocelin.bank.domain.transaction;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Transaction {

    private final TransactionType type;
    private final Double value;
    private final LocalDateTime dataHora;

    public Transaction(TransactionType type, Double value) {
        this.type = type;
        this.value = value;
        this.dataHora = LocalDateTime.now();
    }
}
