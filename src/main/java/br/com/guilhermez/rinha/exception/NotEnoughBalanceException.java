package br.com.guilhermez.rinha.exception;

public class NotEnoughBalanceException extends RuntimeException {

    public NotEnoughBalanceException() {
        super("Insufficient balance to perform the operation");
    }

}