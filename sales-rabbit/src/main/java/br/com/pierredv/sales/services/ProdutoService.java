package br.com.pierredv.sales.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pierredv.sales.data.vo.ProdutoVO;
import br.com.pierredv.sales.entity.Produto;
import br.com.pierredv.sales.exception.ResourceNotFoundException;
import br.com.pierredv.sales.message.ProdutoSendMessage;
import br.com.pierredv.sales.repository.ProdutoRepository;



@Service
public class ProdutoService {
	
	 
	@Autowired
     private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoSendMessage produtoSendMessage;
	
	public ProdutoVO create(ProdutoVO produtoVO) {
		ProdutoVO produtoVoRetorno = ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
		produtoSendMessage.sendMessage(produtoVoRetorno);
		return produtoVoRetorno;
	}
	
	public Page<ProdutoVO> findAll(Pageable pageable) {
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertToProdutoVO);
	}
	
	private ProdutoVO convertToProdutoVO(Produto produto) {
		return ProdutoVO.create(produto);
	}
	
	public ProdutoVO findById(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return ProdutoVO.create(entity);
	}
	
	public ProdutoVO upadte(ProdutoVO produtoVO) {
		final Optional<Produto>optiptionalProduto = produtoRepository.findById(produtoVO.getId());
		
		if(!optiptionalProduto.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
	}
	
	public void delete(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		produtoRepository.delete(entity);
	}
	
}

