package br.com.guilhermez.rinha.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_id_seq")
    @SequenceGenerator(name = "transactions_id_seq", sequenceName = "transactions_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "amount")
    @JsonProperty("valor")
    private Long amount;

    @Column(name = "description")
    @JsonProperty("descricao")
    private String description;

    @Column(name = "transaction_date")
    @JsonProperty("realizada_em")
    private ZonedDateTime date;

    @Column(name = "transaction_type")
    @JsonProperty("tipo")
    private String type;

    @Deprecated
    public TransactionEntity() {
    }

    public TransactionEntity(Long userId, Long amount, String description, String type) {
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.date = ZonedDateTime.now();
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}