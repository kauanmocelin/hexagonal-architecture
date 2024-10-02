package dev.kauanmocelin.bank.application.service.account.exception;

public class AccountNotExistsException extends RuntimeException {

    public AccountNotExistsException(String message) {
        super(message);
    }
}
