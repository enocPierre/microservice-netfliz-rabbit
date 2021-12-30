package br.com.pierredv.sales.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.pierredv.sales.data.vo.ProdutoVO;

@Component
public class ProdutoSendMessage {
	
	@Value("${sale.rabbitmq.exchange}")
	private  String exchange;
	
	@Value("${sale.rabbitmq.routingkey}")
	private  String routingkey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(ProdutoVO produtoVO) {
		rabbitTemplate.convertAndSend(exchange, routingkey, produtoVO);
	}
}

