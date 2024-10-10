package dev.kauanmocelin.bank.account.adapter.output.persistence.jpa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    @Column(nullable = false)
    private Long accountNumber;
    @Column(nullable = false)
    private Double balance;
    @OneToMany
    @JoinColumn(name = "account_id")
    private List<TransactionEntity> transactions;
}
