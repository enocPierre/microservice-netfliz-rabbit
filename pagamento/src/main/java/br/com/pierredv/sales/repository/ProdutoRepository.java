package br.com.pierredv.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pierredv.sales.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
