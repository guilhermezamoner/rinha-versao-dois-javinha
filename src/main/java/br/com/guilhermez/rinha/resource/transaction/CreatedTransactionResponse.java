package br.com.guilhermez.rinha.resource.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatedTransactionResponse {

    @JsonProperty("saldo")
    private Long balance;

    @JsonProperty("limite")
    private Long limit;

    public CreatedTransactionResponse(Long balance, Long limit) {
        this.balance = balance;
        this.limit = limit;
    }

    public Long getBalance() {
        return balance;
    }

    public Long getLimit() {
        return limit;
    }
}
