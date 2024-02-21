package br.com.guilhermez.rinha.resource.balance;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.guilhermez.rinha.entity.TransactionEntity;
import br.com.guilhermez.rinha.entity.UserEntity;

public class StatementResponse {

    @JsonProperty("saldo")
    private BalanceResponse balance;

    @JsonProperty("ultimas_transacoes")
    private List<TransactionEntity> transactions;

    public StatementResponse(UserEntity user, List<TransactionEntity> transactions) {
        this.balance = new BalanceResponse(user);
        this.transactions = transactions;
    }

    public BalanceResponse getBalance() {
        return balance;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }
}
