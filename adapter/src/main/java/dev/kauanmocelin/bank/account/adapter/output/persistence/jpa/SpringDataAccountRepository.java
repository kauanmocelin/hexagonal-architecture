package dev.kauanmocelin.bank.account.adapter.output.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataAccountRepository extends JpaRepository<AccountJpaEntity, Long> {
}