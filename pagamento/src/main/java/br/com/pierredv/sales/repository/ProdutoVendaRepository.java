package br.com.pierredv.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pierredv.sales.entity.ProdutoVenda;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {

}
