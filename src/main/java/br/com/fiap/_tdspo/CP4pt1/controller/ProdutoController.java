package br.com.fiap._tdspo.CP4pt1.controller;

import br.com.fiap._tdspo.CP4pt1.dto.*;
import br.com.fiap._tdspo.CP4pt1.entity.Produto;
import br.com.fiap._tdspo.CP4pt1.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/mercado")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        Produto produto = service.buscarPorId(id);

        ProdutoResponseDTO dto = resposta(produto);

        adicionarLinks(dto, produto.getId());

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dados) {
        Produto produto = service.criar(dados);
        ProdutoResponseDTO dto = resposta(produto);

        adicionarLinks(dto,produto.getId());

        URI URIlocation = linkTo(methodOn(ProdutoController.class)
                .buscarPorId(produto.getId())).toUri();

        return ResponseEntity.created(URIlocation).body(dto);
    }



    @PatchMapping("/{id}/nome")
    public ResponseEntity<ProdutoResponseDTO> atualizarNome(
            @PathVariable Long id,
            @Valid @RequestBody NomeRequestDTO dados) {

        Produto produto = service.atualizarNome(id, dados.nome());

        ProdutoResponseDTO dto = resposta(produto);

        adicionarLinks(dto, produto.getId());

        return ResponseEntity.ok(dto);
    }



    @PatchMapping("/{id}/tipo")
    public ResponseEntity<ProdutoResponseDTO> atualizarTipo(
            @PathVariable Long id,
            @Valid @RequestBody TipoRequestDTO dados) {

        Produto produto = service.atualizarTipo(id, dados.tipo());

        ProdutoResponseDTO dto = resposta(produto);

        adicionarLinks(dto, produto.getId());

        return ResponseEntity.ok(dto);
    }



    @PatchMapping("/{id}/setor")
    public ResponseEntity<ProdutoResponseDTO> atualizarSetor(
            @PathVariable Long id,
            @Valid @RequestBody SetorRequestDTO dados) {

        Produto produto = service.atualizarSetor(id, dados.setor());

        ProdutoResponseDTO dto = resposta(produto);

        adicionarLinks(dto, produto.getId());

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}/tamanho")
    public ResponseEntity<ProdutoResponseDTO> atualizarTamanho(
            @PathVariable Long id,
            @Valid @RequestBody TamanhoRequestDTO dados) {

        Produto produto = service.atualizarTamanho(id, dados.tamanho());

        ProdutoResponseDTO dto = resposta(produto);

        adicionarLinks(dto, produto.getId());

        return ResponseEntity.ok(dto);
    }


    @PatchMapping("/{id}/preco")
    public ResponseEntity<ProdutoResponseDTO> atualizarPreco(
            @PathVariable Long id,
            @Valid @RequestBody PrecoRequestDTO dados) {

        Produto produto = service.atualizarPreco(id, dados.preco());

        ProdutoResponseDTO dto = resposta(produto);

        adicionarLinks(dto, produto.getId());

        return ResponseEntity.ok(dto);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dados) {

        Produto produto = service.atualizar(id, dados);

        ProdutoResponseDTO dto = resposta(produto);

        adicionarLinks(dto, produto.getId());

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> listar() {

        // Implemente conforme o retorno do seu service
        // Exemplo:
        //
        // List<Produto> produtos = service.listar();
        //
        // List<ProdutoResponseDTO> resposta = produtos.stream()
        //         .map(this::resposta)
        //         .peek(dto -> adicionarLinks(dto, dto.getId()))
        //         .toList();

        List<Produto> produtos = service.listar();
        List<ProdutoResponseDTO> resposta = produtos.stream()
                 .map(this::resposta)
                 .peek(dto -> adicionarLinks(dto, dto.getId()))
                 .toList();

        return ResponseEntity.ok(service.listar());
    }

    private ProdutoResponseDTO resposta(Produto produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.toProdutoDto(produto);
        return dto;
    }

    private void adicionarLinks(ProdutoResponseDTO dto, Long id) {

        // Link para consultar o próprio produto
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .buscarPorId(id)
                ).withSelfRel()
        );

        // Link para atualizar o produto inteiro
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .atualizar(id, null)
                ).withRel("atualizar")
        );

        // Link para alterar nome
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .atualizarNome(id, null)
                ).withRel("atualizar-nome")
        );

        // Link para alterar tipo
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .atualizarTipo(id, null)
                ).withRel("atualizar-tipo")
        );

        // Link para alterar setor
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .atualizarSetor(id, null)
                ).withRel("atualizar-setor")
        );

        // Link para alterar tamanho
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .atualizarTamanho(id, null)
                ).withRel("atualizar-tamanho")
        );

        // Link para alterar preço
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .atualizarPreco(id, null)
                ).withRel("atualizar-preco")
        );

        // Link para deletar
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .deletar(id)
                ).withRel("deletar")
        );

        // Link para voltar à lista de produtos
        dto.add(
                linkTo(
                        methodOn(ProdutoController.class)
                                .listar()
                ).withRel("produtos")
        );
    }
}
