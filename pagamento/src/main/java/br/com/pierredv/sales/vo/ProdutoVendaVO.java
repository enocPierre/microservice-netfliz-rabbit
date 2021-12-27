package br.com.pierredv.sales.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.pierredv.sales.entity.ProdutoVenda;

@JsonPropertyOrder({"id", "idProduto", "quantidade","venda" })
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("id")
	private Long idProduto;
	
	@JsonProperty("id")
	private Integer quantidade;

	
	public ProdutoVendaVO() {
		
	}

	public ProdutoVendaVO(Long id, Long idProduto, Integer quantidade) {
		super();
		this.id = id;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "ProdutoVendaVO [id=" + id + ", idProduto=" + idProduto + ", quantidade=" + quantidade + "]";
	}
	
	public static ProdutoVendaVO create(ProdutoVenda produtoVenda) {
		return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
	}
}
