package br.com.on.fiap.adapter.output.api.datasource;

import br.com.on.fiap.adapter.infrastructure.exception.IntegracaoPagamentoExcecao;
import br.com.on.fiap.adapter.output.api.mercadopago.client.IntegracaoPagamento;
import br.com.on.fiap.adapter.output.api.mercadopago.dto.PagamentoRespostaIntegracaoDTO;
import br.com.on.fiap.adapter.output.api.mercadopago.dto.PagamentoSolicitacaoIntegracaoDTO;
import br.com.on.fiap.core.adapter.datasource.PagamentoIntegracaoDataSource;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.domain.Pagamento;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PagamentoIntegracaoDataSourceImpl implements PagamentoIntegracaoDataSource {

    private final IntegracaoPagamento integracaoPagamento;

    public PagamentoIntegracaoDataSourceImpl(IntegracaoPagamento integracaoPagamento) {
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
