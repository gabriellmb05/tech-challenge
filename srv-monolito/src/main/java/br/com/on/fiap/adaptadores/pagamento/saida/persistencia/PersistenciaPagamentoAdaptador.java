package br.com.on.fiap.adaptadores.pagamento.saida.persistencia;

import br.com.on.fiap.adaptadores.pagamento.saida.persistencia.mapeador.PagamentoSaidaMapeador;
import br.com.on.fiap.adaptadores.pagamento.saida.persistencia.repositorio.PagamentoRepositorio;
import br.com.on.fiap.entidade.PagamentoEntidade;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersistenciaPagamentoAdaptador implements PersistePagamentoPortaSaida {

    private final PagamentoRepositorio pagamentoRepositorio;
    private final PagamentoSaidaMapeador pagamentoSaidaMapeador;

    public PersistenciaPagamentoAdaptador(
            PagamentoRepositorio pagamentoRepositorio, PagamentoSaidaMapeador pagamentoSaidaMapeador) {
        this.pagamentoRepositorio = pagamentoRepositorio;
        this.pagamentoSaidaMapeador = pagamentoSaidaMapeador;
    }

    @Override
    @Transactional
    public Pagamento salvaPagamento(Pagamento pagamento) {
        PagamentoEntidade pagamentoEntidade = pagamentoSaidaMapeador.paraEntidade(pagamento);
        PagamentoEntidade pagamentoSalvo = pagamentoRepositorio.save(pagamentoEntidade);
        return pagamentoSaidaMapeador.paraPagamento(pagamentoSalvo);
    }

    @Override
    public Pagamento salvaPagamentoFinalizado(Pagamento pagamento) {
        PagamentoEntidade pagamentoEntidade = pagamentoSaidaMapeador.paraEntidadeComPagamentoAprovado(pagamento);
        PagamentoEntidade pagamentoSalvo = pagamentoRepositorio.save(pagamentoEntidade);
        return pagamentoSaidaMapeador.paraPagamento(pagamentoSalvo);
    }
}
