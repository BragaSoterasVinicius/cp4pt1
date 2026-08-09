package br.com.fiap._tdspo.CP4pt1.service;

import br.com.fiap._tdspo.CP4pt1.entity.Produto;
import br.com.fiap._tdspo.CP4pt1.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto criar(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto findById(Long id){
        return produtoRepository.getReferenceById(id);
    }
}
