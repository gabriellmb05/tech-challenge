package br.com.on.fiap.adapter.output.datasource;

import br.com.on.fiap.adapter.output.api.mercadopago.client.IntegracaoPagamento;
import br.com.on.fiap.adapter.output.api.mercadopago.dto.PagamentoRespostaIntegracaoDTO;
import br.com.on.fiap.adapter.output.api.mercadopago.dto.PagamentoSolicitacaoIntegracaoDTO;
import br.com.on.fiap.adapter.output.persistence.entity.PagamentoEntity;
import br.com.on.fiap.adapter.output.persistence.repository.PagamentoRepository;
import br.com.on.fiap.core.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.infrastructure.exception.IntegracaoPagamentoExcecao;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
public class PagamentoDataSourceImpl implements PagamentoDataSource {

    private final IntegracaoPagamento integracaoPagamento;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoDataSourceImpl(IntegracaoPagamento integracaoPagamento, PagamentoRepository pagamentoRepository) {
        this.integracaoPagamento = integracaoPagamento;
        this.pagamentoRepository = pagamentoRepository;
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

    @Override
    @Transactional
    public Pagamento salvaPagamento(Pagamento pagamento) {
        PagamentoEntity pagamentoEntity = PagamentoEntity.fromDomain(pagamento);
        PagamentoEntity pagamentoSalvo = pagamentoRepository.save(pagamentoEntity);
        return pagamentoSalvo.toDomain();
    }
}
