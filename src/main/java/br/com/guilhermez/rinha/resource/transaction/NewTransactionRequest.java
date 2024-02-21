package br.com.guilhermez.rinha.resource.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record NewTransactionRequest (
        @Positive
        Long valor,
        @Pattern(regexp = "d|c") @NotBlank @Size(min = 1, max = 1)
        String tipo,
        @NotBlank @Size(max = 10)
        String descricao
) {
}