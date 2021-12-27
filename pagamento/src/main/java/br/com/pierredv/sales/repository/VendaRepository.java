package br.com.pierredv.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pierredv.sales.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}
