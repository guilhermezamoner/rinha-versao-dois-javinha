package br.com.guilhermez.rinha.entity;

import br.com.guilhermez.rinha.exception.NotEnoughBalanceException;
import br.com.guilhermez.rinha.resource.transaction.NewTransactionRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_limit")
    private Long limit;

    @Column(name = "balance")
    private Long balance;

    @Deprecated
    public UserEntity() {
    }

    public UserEntity(Long limit, Long balance){
        this.limit = limit;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public Long getLimit() {
        return limit;
    }

    public Long getBalance() {
        return balance;
    }

    public void credit(Long amount){
        this.balance += amount;
    }

    public void debit(Long amount){
        if((this.limit + this.balance) < amount) {
            throw new NotEnoughBalanceException();
        }

        this.balance -= amount;
    }

    public TransactionEntity processTransaction(NewTransactionRequest request){
        if("c".equalsIgnoreCase(request.tipo())){
            this.credit(request.valor());
        } else if("d".equalsIgnoreCase(request.tipo())){
            this.debit(request.valor());
        }

        return createTransaction(request);
    }

    private TransactionEntity createTransaction(NewTransactionRequest request){
        return new TransactionEntity(this.id, request.valor(), request.descricao(), request.tipo());
    }

}