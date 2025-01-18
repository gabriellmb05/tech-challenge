package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.PagamentoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.swagger.PagamentoControladorSwagger;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.AtualizaPagamentoPortaEntrada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class PagamentoControlador implements PagamentoControladorSwagger {

    private final AtualizaPagamentoPortaEntrada atualizaPagamentoPortaEntrada;
    private final PagamentoEntradaMapeador pagamentoEntradaMapeador;

    public PagamentoControlador(
            AtualizaPagamentoPortaEntrada atualizaPagamentoPortaEntrada,
            PagamentoEntradaMapeador pagamentoEntradaMapeador) {
        this.atualizaPagamentoPortaEntrada = atualizaPagamentoPortaEntrada;
        this.pagamentoEntradaMapeador = pagamentoEntradaMapeador;
    }

    @Override
    @PutMapping("/{nrProtocolo}")
    public ResponseEntity<PagamentoRespostaDTO> atualizaPagamento(@PathVariable("numeroProtocolo") String nrProtocolo) {
        Pagamento pagamentoAtualizado = atualizaPagamentoPortaEntrada.atualizaPagamento(nrProtocolo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(pagamentoEntradaMapeador.paraPagamentoDTO(pagamentoAtualizado));
    }
}
