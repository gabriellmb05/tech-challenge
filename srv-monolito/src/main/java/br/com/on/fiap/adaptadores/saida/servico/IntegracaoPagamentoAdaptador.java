package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.integracao.IntegracaoPagamento;
import br.com.on.fiap.adaptadores.integracao.excecao.IntegracaoPagamentoExcecao;
import br.com.on.fiap.adaptadores.integracao.solicitacao.PagamentoRespostaIntegracaoDTO;
import br.com.on.fiap.adaptadores.integracao.solicitacao.PagamentoSolicitacaoIntegracaoDTO;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.saida.integracao.IntegracaoPagamentoSaida;
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
                throw new IntegracaoPagamentoExcecao(
                        MessageError.MSG_ERRO_CODIGO_STATUS_DIFERENTE_OK.getMensagem(), res.getStatusCode());
            }
        } catch (IntegracaoPagamentoExcecao e) {
            throw e;
        } catch (Exception e) {
            throw new IntegracaoPagamentoExcecao(
                    MessageError.MSG_ERRO_INESPERADO_INTEGRACAO.getMensagem(), e.getMessage());
        }
    }
}
