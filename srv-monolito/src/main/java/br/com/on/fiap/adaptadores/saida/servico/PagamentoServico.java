package br.com.on.fiap.adaptadores.saida.servico;

import java.util.UUID;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.InfoAdicionaisDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.PagamentoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.PagamentoSolicitacaoDTO;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServico {

	public PagamentoRespostaDTO criarPagamentoMockResposta(PagamentoSolicitacaoDTO pagamentoSolicitacaoDTO) {
		PagamentoDTO pagamentoDTO = PagamentoDTO.builder()
				.installments(pagamentoSolicitacaoDTO.getPagamentoDTO().getInstallments())
				.type(pagamentoSolicitacaoDTO.getPagamentoDTO().getType())
				.installmentsCost(pagamentoSolicitacaoDTO.getPagamentoDTO().getInstallmentsCost()).build();

		InfoAdicionaisDTO additionalInfo = InfoAdicionaisDTO.builder()
				.externalReference(pagamentoSolicitacaoDTO.getInfoAdicionaisDTO().getExternalReference())
				.printOnTerminal(true).build();
		return PagamentoRespostaDTO.builder().id(UUID.randomUUID().toString()).deviceId("PAX_A910__SMARTPOS1234345545")
				.amount(pagamentoSolicitacaoDTO.getAmount()).description(pagamentoSolicitacaoDTO.getDescription())
				.pagamentoDTO(pagamentoDTO).infoAdicionaisDTO(additionalInfo).build();
	}
}
