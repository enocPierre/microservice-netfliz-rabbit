package br.com.pierredv.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pierredv.sales.entity.Produto;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

}

