package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.PagamentoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.swagger.PagamentoControladorSwagger;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.AtualizaPagamentoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.ValidaPagamentoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.DetalhaPedidoPortaEntrada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagamentos")
public class PagamentoControlador implements PagamentoControladorSwagger {

    private final AtualizaPagamentoPortaEntrada atualizaPagamentoPortaEntrada;
    private final PagamentoEntradaMapeador pagamentoEntradaMapeador;
    private final DetalhaPedidoPortaEntrada detalhaPedidoPortaEntrada;
    private final ValidaPagamentoPortaEntrada validaPagamentoPortaEntrada;

    public PagamentoControlador(
            AtualizaPagamentoPortaEntrada atualizaPagamentoPortaEntrada,
            PagamentoEntradaMapeador pagamentoEntradaMapeador,
            DetalhaPedidoPortaEntrada detalhaPedidoPortaEntrada,
            ValidaPagamentoPortaEntrada validaPagamentoPortaEntrada) {
        this.atualizaPagamentoPortaEntrada = atualizaPagamentoPortaEntrada;
        this.pagamentoEntradaMapeador = pagamentoEntradaMapeador;
        this.detalhaPedidoPortaEntrada = detalhaPedidoPortaEntrada;
        this.validaPagamentoPortaEntrada = validaPagamentoPortaEntrada;
    }

    @Override
    @PutMapping("/{nrProtocolo}")
    public ResponseEntity<PagamentoRespostaDTO> atualizaPagamento(@PathVariable("nrProtocolo") String nrProtocolo) {
        Pagamento pagamento =
                detalhaPedidoPortaEntrada.detalhaPedido(nrProtocolo).getPagamento();
        validaPagamentoPortaEntrada.validarPagamentoJaRealizado(pagamento, nrProtocolo);
        Pagamento pagamentoAtualizado = atualizaPagamentoPortaEntrada.atualizaPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.OK)
                .body(pagamentoEntradaMapeador.paraPagamentoDTO(pagamentoAtualizado));
    }
}
