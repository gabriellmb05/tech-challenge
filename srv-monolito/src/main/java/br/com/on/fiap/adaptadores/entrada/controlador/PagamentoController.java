package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.PagamentoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.saida.servico.PagamentoServico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagamentos")
public class PagamentoController implements PagamentoControllerSwagger {
	private final Logger log = LoggerFactory.getLogger(PagamentoController.class);

	private final PagamentoServico pagamentoServico;

	public PagamentoController(PagamentoServico pagamentoServico) {
		this.pagamentoServico = pagamentoServico;
	}

	@Override
	@PostMapping
	public ResponseEntity<PagamentoRespostaDTO> criarPagamento(
			@RequestBody PagamentoSolicitacaoDTO pagamentoSolicitacaoDTO) {
		log.info("Request: {}", pagamentoSolicitacaoDTO);
		PagamentoRespostaDTO response = pagamentoServico.criarPagamentoMockResposta(pagamentoSolicitacaoDTO);
		log.info("Response: {}", response);
		return ResponseEntity.ok(response);
	}
}
