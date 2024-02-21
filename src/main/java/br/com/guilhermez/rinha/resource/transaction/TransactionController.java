package br.com.guilhermez.rinha.resource.transaction;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilhermez.rinha.entity.TransactionEntity;
import br.com.guilhermez.rinha.entity.UserEntity;
import br.com.guilhermez.rinha.repository.TransactionRepository;
import br.com.guilhermez.rinha.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionController(
            UserRepository userRepository,
            TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    @PostMapping("/clientes/{id}/transacoes")
    public ResponseEntity<CreatedTransactionResponse> createTransaction(
            @PathVariable("id") Long id,
            @RequestBody @Valid NewTransactionRequest request){

        Optional<UserEntity> optionalUser = userRepository.findFirstById(id);

        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        UserEntity user = optionalUser.get();

        TransactionEntity newTransactionEntity = user.processTransaction(request);

        userRepository.save(user);
        transactionRepository.save(newTransactionEntity);

        return ResponseEntity.ok().body(new CreatedTransactionResponse(user.getBalance(), user.getLimit()));
    }

}
