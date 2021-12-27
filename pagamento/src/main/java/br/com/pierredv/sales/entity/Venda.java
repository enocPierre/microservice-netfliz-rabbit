package br.com.pierredv.sales.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "data", nullable = false)
	private Date data;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH )
	private List<ProdutoVenda> produtos;
	
	@Column(name = "valorTotal", nullable = false, length = 10)
	private Double valorTotal;
	
	
	public Venda() {
		
	}


	public Venda(Long id, Date data, List<ProdutoVenda> produtos, Double valorTotal) {
		super();
		this.id = id;
		this.data = data;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public List<ProdutoVenda> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<ProdutoVenda> produtos) {
		this.produtos = produtos;
	}


	public Double getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}


	@Override
	public String toString() {
		return "Venda [id=" + id + ", data=" + data + ", produtos=" + produtos + ", valorTotal=" + valorTotal + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}
	

}
