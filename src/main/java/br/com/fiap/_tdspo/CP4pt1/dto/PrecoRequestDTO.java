package br.com.fiap._tdspo.CP4pt1.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PrecoRequestDTO(@NotNull @DecimalMin(value = "0.00") BigDecimal preco) {
}
