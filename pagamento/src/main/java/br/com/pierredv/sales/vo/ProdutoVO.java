package br.com.pierredv.sales.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.pierredv.sales.entity.Produto;

@JsonPropertyOrder({"id", "estoque"})
public class ProdutoVO  extends RepresentationModel<ProdutoVO> implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("id")
	private Integer estoque;
	
	public ProdutoVO() {
		
	}

	public ProdutoVO(Long id, Integer estoque) {
		this.id = id;
		this.estoque = estoque;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "ProdutoVO [id=" + id + ", estoque=" + estoque + "]";
	}
	
	public static ProdutoVO create(Produto Produto) {
		return new ModelMapper().map(Produto, ProdutoVO.class);
	}
}
