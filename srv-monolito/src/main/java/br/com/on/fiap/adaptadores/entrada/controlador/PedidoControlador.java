package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.PedidoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.swagger.PedidoControladorSwagger;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.portas.entrada.pedido.InserePedidoPortaEntrada;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
public class PedidoControlador implements PedidoControladorSwagger {

	private final InserePedidoPortaEntrada inserePedidoPortaEntrada;
	private final PedidoEntradaMapeador pedidoEntradaMapeador;

	public PedidoControlador(InserePedidoPortaEntrada inserePedidoPortaEntrada,
			PedidoEntradaMapeador pedidoEntradaMapeador) {
		this.inserePedidoPortaEntrada = inserePedidoPortaEntrada;
		this.pedidoEntradaMapeador = pedidoEntradaMapeador;
	}

	@Override
	@PostMapping
	public ResponseEntity<PedidoRespostaDTO> inserePedido(
			@Valid @RequestBody PedidoSolicitacaoDTO pedidoSolicitacaoDTO) {
		Pedido pedido = pedidoEntradaMapeador.paraPedido(pedidoSolicitacaoDTO);
		Pedido pedidoPersistido = inserePedidoPortaEntrada.inserir(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoEntradaMapeador.paraPedidoDTO(pedidoPersistido));
	}

}
