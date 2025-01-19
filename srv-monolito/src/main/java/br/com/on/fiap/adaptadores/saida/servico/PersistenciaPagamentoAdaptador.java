package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PagamentoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PagamentoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PagamentoRepositorio;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
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
