package br.com.guilhermez.rinha.resource.balance;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilhermez.rinha.entity.TransactionEntity;
import br.com.guilhermez.rinha.entity.UserEntity;
import br.com.guilhermez.rinha.repository.TransactionRepository;
import br.com.guilhermez.rinha.repository.UserRepository;

@RestController
public class BalanceController {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public BalanceController(
            UserRepository userRepository,
            TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/clientes/{id}/extrato")
    public ResponseEntity<StatementResponse> getTransactions(@PathVariable("id") Long id) {
        List<TransactionEntity> transactions = transactionRepository.findTop10ByUserIdOrderByIdDesc(id);

        Optional<UserEntity> user =  userRepository.findById(id);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new StatementResponse(user.get(), transactions));
    }

}