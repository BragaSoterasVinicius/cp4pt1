package br.com.fiap._tdspo.CP4pt1.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TamanhoRequestDTO(@NotNull @DecimalMin(value = "0.01") BigDecimal tamanho) {
}
