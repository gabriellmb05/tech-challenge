package br.com.on.fiap.adapter.output.db.datasource;

import br.com.on.fiap.adapter.output.db.entity.PagamentoEntity;
import br.com.on.fiap.adapter.output.db.repository.PagamentoRepository;
import br.com.on.fiap.core.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.core.domain.Pagamento;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
public class PagamentoDataSourceImpl implements PagamentoDataSource {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoDataSourceImpl(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    @Transactional
    public Pagamento salvaPagamento(Pagamento pagamento) {
        PagamentoEntity pagamentoEntity = PagamentoEntity.fromDomain(pagamento);
        PagamentoEntity pagamentoSalvo = pagamentoRepository.save(pagamentoEntity);
        return pagamentoSalvo.toDomain();
    }
}
