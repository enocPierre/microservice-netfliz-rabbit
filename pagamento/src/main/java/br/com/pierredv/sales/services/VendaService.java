package br.com.pierredv.sales.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pierredv.sales.entity.ProdutoVenda;
import br.com.pierredv.sales.entity.Venda;
import br.com.pierredv.sales.exception.ResourceNotFoundException;
import br.com.pierredv.sales.repository.ProdutoVendaRepository;
import br.com.pierredv.sales.repository.VendaRepository;
import br.com.pierredv.sales.vo.VendaVO;

@Service
public class VendaService {
	
	@Autowired
    private VendaRepository vendaRepository;
	
	@Autowired
	private ProdutoVendaRepository produtoVendaRepository;
	
	// venda vem com lista de produto
	public VendaVO create(VendaVO vendaVO) {
		Venda venda = vendaRepository.save(Venda.create(vendaVO));
		
		List<ProdutoVenda> produtosSalvos = new ArrayList<>();
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtosSalvos.add(produtoVendaRepository.save(pv));
		});
		
		return vendaVO.create(venda);
	}
	
	public Page<VendaVO> findAll(Pageable pageable) {
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertToVendaVO);
	}
	
	private VendaVO convertToVendaVO(Venda venda) {
		return VendaVO.create(venda);
	}
	
	public VendaVO findById(Long id) {
		var entity = vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return VendaVO.create(entity);
	}
	
	public VendaVO upadte(VendaVO vendaVO) {
		final Optional<Venda>optiptionalProduto = vendaRepository.findById(vendaVO.getId());
		
		if(!optiptionalProduto.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return VendaVO.create(vendaRepository.save(Venda.create(vendaVO)));
	}
	
	public void delete(Long id) {
		var entity = vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		vendaRepository.delete(entity);
	}

}
