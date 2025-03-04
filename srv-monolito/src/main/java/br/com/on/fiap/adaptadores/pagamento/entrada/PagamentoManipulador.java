package br.com.on.fiap.adaptadores.pagamento.entrada;

import br.com.on.fiap.adaptadores.pagamento.PagamentoControladorSwagger;
import br.com.on.fiap.adaptadores.pagamento.entrada.dto.resposta.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.pagamento.entrada.mapeador.PagamentoEntradaMapeador;
import br.com.on.fiap.hexagono.casodeuso.pagamento.entrada.AtualizaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pagamento.entrada.ValidaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Pagamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagamentos")
public class PagamentoManipulador implements PagamentoControladorSwagger {

    private final AtualizaPagamentoCasoDeUso atualizaPagamentoCasoDeUso;
    private final PagamentoEntradaMapeador pagamentoEntradaMapeador;
    private final DetalhaPedidoCasoDeUso detalhaPedidoCasoDeUso;
    private final ValidaPagamentoCasoDeUso validaPagamentoCasoDeUso;

    public PagamentoManipulador(
            AtualizaPagamentoCasoDeUso atualizaPagamentoCasoDeUso,
            PagamentoEntradaMapeador pagamentoEntradaMapeador,
            DetalhaPedidoCasoDeUso detalhaPedidoCasoDeUso,
            ValidaPagamentoCasoDeUso validaPagamentoCasoDeUso) {
        this.atualizaPagamentoCasoDeUso = atualizaPagamentoCasoDeUso;
        this.pagamentoEntradaMapeador = pagamentoEntradaMapeador;
        this.detalhaPedidoCasoDeUso = detalhaPedidoCasoDeUso;
        this.validaPagamentoCasoDeUso = validaPagamentoCasoDeUso;
    }

    @Override
    @PutMapping("/{nrProtocolo}")
    public ResponseEntity<PagamentoRespostaDTO> atualizaPagamento(@PathVariable("nrProtocolo") String nrProtocolo) {
        Pagamento pagamento = detalhaPedidoCasoDeUso.detalhaPedido(nrProtocolo).getPagamento();
        validaPagamentoCasoDeUso.validarPagamentoJaRealizado(pagamento, nrProtocolo);
        Pagamento pagamentoAtualizado = atualizaPagamentoCasoDeUso.atualizaPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.OK)
                .body(pagamentoEntradaMapeador.paraPagamentoDTO(pagamentoAtualizado));
    }
}
