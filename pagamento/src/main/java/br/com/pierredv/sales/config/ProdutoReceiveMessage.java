package br.com.pierredv.sales.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.pierredv.sales.entity.Produto;
import br.com.pierredv.sales.repository.ProdutoRepository;
import br.com.pierredv.sales.vo.ProdutoVO;

@Component
public class ProdutoReceiveMessage {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RabbitListener(queues = {"${sale.rabbitmq.queue}"})
	public void receive(@Payload ProdutoVO produtoVO) {
		produtoRepository.save(Produto.create(produtoVO));
	}
}
