package br.com.guilhermez.rinha.resource.balance;

import br.com.guilhermez.rinha.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class BalanceResponse {

    @JsonProperty("total")
    private Long amount;

    @JsonProperty("data_extrato")
    private ZonedDateTime date;

    @JsonProperty("limite")
    private Long limit;

    public BalanceResponse(UserEntity user) {
        this.amount = user.getBalance();
        this.limit = user.getLimit();
        this.date = ZonedDateTime.now();
    }

    public Long getAmount() {
        return amount;
    }

    public Long getLimit() {
        return limit;
    }

    public ZonedDateTime getDate() {
        return date;
    }
}
