package br.com.fiap._tdspo.CP4pt1.controller;

import br.com.fiap._tdspo.CP4pt1.dto.*;
import br.com.fiap._tdspo.CP4pt1.entity.Produto;
import br.com.fiap._tdspo.CP4pt1.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/mercado")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dados) {
        Produto produto = service.criar(dados);
        return ResponseEntity.created(URI.create("/mercado/" + produto.getId())).body(resposta(produto));
    }

    @PatchMapping("/{id}/nome")
    public ProdutoResponseDTO atualizarNome(@PathVariable Long id, @Valid @RequestBody NomeRequestDTO dados) {
        return resposta(service.atualizarNome(id, dados.nome()));
    }

    @PatchMapping("/{id}/tipo")
    public ProdutoResponseDTO atualizarTipo(@PathVariable Long id, @Valid @RequestBody TipoRequestDTO dados) {
        return resposta(service.atualizarTipo(id, dados.tipo()));
    }

    @PatchMapping("/{id}/setor")
    public ProdutoResponseDTO atualizarSetor(@PathVariable Long id, @Valid @RequestBody SetorRequestDTO dados) {
        return resposta(service.atualizarSetor(id, dados.setor()));
    }

    @PatchMapping("/{id}/tamanho")
    public ProdutoResponseDTO atualizarTamanho(@PathVariable Long id, @Valid @RequestBody TamanhoRequestDTO dados) {
        return resposta(service.atualizarTamanho(id, dados.tamanho()));
    }

    @PatchMapping("/{id}/preco")
    public ProdutoResponseDTO atualizarPreco(@PathVariable Long id, @Valid @RequestBody PrecoRequestDTO dados) {
        return resposta(service.atualizarPreco(id, dados.preco()));
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dados) {
        return resposta(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private ProdutoResponseDTO resposta(Produto produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.toProdutoDto(produto);
        return dto;
    }
}
