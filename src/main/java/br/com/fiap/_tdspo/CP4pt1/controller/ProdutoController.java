package br.com.fiap._tdspo.CP4pt1.controller;

import br.com.fiap._tdspo.CP4pt1.dto.ProdutoResponseDTO;
import br.com.fiap._tdspo.CP4pt1.entity.Produto;
import br.com.fiap._tdspo.CP4pt1.repository.ProdutoRepository;
import br.com.fiap._tdspo.CP4pt1.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/mercado")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody Produto produto){
        Produto criado = service.criar(produto);

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.toProdutoDto(produto);

        // Adicionar esses negocio no fim de cada controller pra reotrna rcom o link das outras chamadas
        // response.add(
        //                linkTo(methodOn(BdController.class)
        //                        .findById(created.getId()))
        //                        .withSelfRel()
        //        );
        return ResponseEntity.created(
                linkTo(methodOn(ProdutoResponseDTO.class)
                       // adicionar essa linha quando fazer o metodo findById nesse controller
                        // .findById(criado.getId()))
                        .toUri()
        ).body(produtoResponseDTO);
    }
}
