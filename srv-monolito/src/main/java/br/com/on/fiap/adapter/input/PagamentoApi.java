package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.swagger.PagamentoApiSwagger;
import br.com.on.fiap.core.adapter.controller.PagamentoController;
import br.com.on.fiap.core.application.dto.resposta.PagamentoResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagamentos")
public class PagamentoApi implements PagamentoApiSwagger {

    private final PagamentoController pagamentoController;

    public PagamentoApi(PagamentoController pagamentoController) {
        this.pagamentoController = pagamentoController;
    }

    @Override
    @PutMapping("/{nrProtocolo}")
    public ResponseEntity<PagamentoResposta> atualizaPagamento(@PathVariable("nrProtocolo") String nrProtocolo) {
        PagamentoResposta pagamento = pagamentoController.atualizaPagamento(nrProtocolo);
        return ResponseEntity.status(HttpStatus.OK).body(pagamento);
    }
}
