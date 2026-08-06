package br.com.fiap._tdspo.CP4pt1.repository;

import br.com.fiap._tdspo.CP4pt1.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
