package br.com.on.fiap.adaptadores.pagamento.saida.api.mercadopago;

import br.com.on.fiap.adaptadores.pagamento.saida.api.mercadopago.dto.resposta.PagamentoRespostaIntegracaoDTO;
import br.com.on.fiap.adaptadores.pagamento.saida.api.mercadopago.dto.solicitacao.PagamentoSolicitacaoIntegracaoDTO;
import br.com.on.fiap.adaptadores.pagamento.saida.api.mercadopago.integracao.IntegracaoPagamento;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.entidades.Pagamento;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.infraestrutura.excecao.IntegracaoPagamentoExcecao;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class IntegracaoPagamentoAdaptador implements IntegracaoPagamentoSaida {

    private final IntegracaoPagamento integracaoPagamento;

    public IntegracaoPagamentoAdaptador(IntegracaoPagamento integracaoPagamento) {
        this.integracaoPagamento = integracaoPagamento;
    }

    @Override
    public void integracaoEnviaPagamento(Pagamento pagamento) {
        try {
            ResponseEntity<PagamentoRespostaIntegracaoDTO> res = integracaoPagamento.enviarPagamento(
                    PagamentoSolicitacaoIntegracaoDTO.criarPagamentoIntegracao(pagamento));

            if (res.getStatusCode().equals(HttpStatus.OK)) {
                log.info("Pagamento realizado com sucesso");
            } else {
                log.error(res.getStatusCode());
                throw new IntegracaoPagamentoExcecao(MessageError.MSG_ERRO_INTEGRACAO_INESPERADO.getMensagem());
            }
        } catch (IntegracaoPagamentoExcecao e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IntegracaoPagamentoExcecao(MessageError.MSG_ERRO_INTEGRACAO_INESPERADO.getMensagem());
        }
    }
}
