package br.com.fiap._tdspo.CP4pt1.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/** Dados informados pelo cliente ao criar ou substituir um produto. */
public record ProdutoRequestDTO(
        @NotBlank String nome,
        @NotBlank String tipo,
        @NotBlank String setor,
        @NotNull @DecimalMin(value = "0.01") BigDecimal tamanho,
        @NotNull @DecimalMin(value = "0.00") BigDecimal preco
) {
}
