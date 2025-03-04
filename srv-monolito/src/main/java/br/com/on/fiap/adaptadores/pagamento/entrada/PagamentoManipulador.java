package br.com.on.fiap.adaptadores.pagamento.entrada;

import br.com.on.fiap.adaptadores.pagamento.PagamentoControladorSwagger;
import br.com.on.fiap.adaptadores.pagamento.entrada.dto.resposta.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.pagamento.entrada.mapeador.PagamentoEntradaMapeador;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import br.com.on.fiap.hexagono.application.usecase.pagamento.base.PagamentoAtualizaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pagamento.base.PagamentoValidaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pedido.base.PedidoDetalhaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagamentos")
public class PagamentoManipulador implements PagamentoControladorSwagger {

    private final PagamentoAtualizaUseCase pagamentoAtualizaUseCase;
    private final PagamentoEntradaMapeador pagamentoEntradaMapeador;
    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final PagamentoValidaUseCase pagamentoValidaUseCase;

    public PagamentoManipulador(
            PagamentoAtualizaUseCase pagamentoAtualizaUseCase,
            PagamentoEntradaMapeador pagamentoEntradaMapeador,
            PedidoDetalhaUseCase pedidoDetalhaUseCase,
            PagamentoValidaUseCase pagamentoValidaUseCase) {
        this.pagamentoAtualizaUseCase = pagamentoAtualizaUseCase;
        this.pagamentoEntradaMapeador = pagamentoEntradaMapeador;
        this.pedidoDetalhaUseCase = pedidoDetalhaUseCase;
        this.pagamentoValidaUseCase = pagamentoValidaUseCase;
    }

    @Override
    @PutMapping("/{nrProtocolo}")
    public ResponseEntity<PagamentoRespostaDTO> atualizaPagamento(@PathVariable("nrProtocolo") String nrProtocolo) {
        Pagamento pagamento = pedidoDetalhaUseCase.detalhaPedido(nrProtocolo).getPagamento();
        pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo);
        Pagamento pagamentoAtualizado = pagamentoAtualizaUseCase.atualizaPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.OK)
                .body(pagamentoEntradaMapeador.paraPagamentoDTO(pagamentoAtualizado));
    }
}
