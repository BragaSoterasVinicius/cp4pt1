package br.com.fiap._tdspo.CP4pt1.dto;

import jakarta.validation.constraints.NotBlank;

public record SetorRequestDTO(@NotBlank String setor) {
}
