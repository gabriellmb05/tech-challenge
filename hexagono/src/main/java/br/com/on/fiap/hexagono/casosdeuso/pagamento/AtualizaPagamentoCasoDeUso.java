package br.com.on.fiap.hexagono.casosdeuso.pagamento;

import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.AtualizaPagamentoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.integracao.IntegracaoPagamentoSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.DetalhaPedidoPortaSaida;
import java.util.Optional;

public class AtualizaPagamentoCasoDeUso implements AtualizaPagamentoPortaEntrada {

    private final IntegracaoPagamentoSaida integracaoPagamentoSaida;
    private final DetalhaPedidoPortaSaida detalhaPedidoPortaSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    public AtualizaPagamentoCasoDeUso(
            IntegracaoPagamentoSaida integracaoPagamentoSaida,
            DetalhaPedidoPortaSaida detalhaPedidoPortaSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        this.integracaoPagamentoSaida = integracaoPagamentoSaida;
        this.detalhaPedidoPortaSaida = detalhaPedidoPortaSaida;
        this.persistePagamentoPortaSaida = persistePagamentoPortaSaida;
    }

    @Override
    public Pagamento atualizaPagamento(String nrProtocolo) {
        Optional<Pedido> pedido = detalhaPedidoPortaSaida.detalhaPedido(nrProtocolo);
        integracaoPagamentoSaida.integracaoEnviaPagamento(pedido.get().getPagamento());
        return persistePagamentoPortaSaida.salvaPagamentoFinalizado(pedido.get().getPagamento());
    }
}
