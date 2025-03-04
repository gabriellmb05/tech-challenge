package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.response.PagamentoRespostaDTO;
import br.com.on.fiap.adapter.input.mapper.PagamentoInputMapper;
import br.com.on.fiap.adapter.input.swagger.PagamentoApiSwagger;
import br.com.on.fiap.hexagono.application.usecase.pagamento.PagamentoAtualizaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pagamento.PagamentoValidaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagamentos")
public class PagamentoApi implements PagamentoApiSwagger {

    private final PagamentoAtualizaUseCase pagamentoAtualizaUseCase;
    private final PagamentoInputMapper pagamentoInputMapper;
    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final PagamentoValidaUseCase pagamentoValidaUseCase;

    public PagamentoApi(
            PagamentoAtualizaUseCase pagamentoAtualizaUseCase,
            PagamentoInputMapper pagamentoInputMapper,
            PedidoDetalhaUseCase pedidoDetalhaUseCase,
            PagamentoValidaUseCase pagamentoValidaUseCase) {
        this.pagamentoAtualizaUseCase = pagamentoAtualizaUseCase;
        this.pagamentoInputMapper = pagamentoInputMapper;
        this.pedidoDetalhaUseCase = pedidoDetalhaUseCase;
        this.pagamentoValidaUseCase = pagamentoValidaUseCase;
    }

    @Override
    @PutMapping("/{nrProtocolo}")
    public ResponseEntity<PagamentoRespostaDTO> atualizaPagamento(@PathVariable("nrProtocolo") String nrProtocolo) {
        Pagamento pagamento = pedidoDetalhaUseCase.detalhaPedido(nrProtocolo).getPagamento();
        pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo);
        Pagamento pagamentoAtualizado = pagamentoAtualizaUseCase.atualizaPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoInputMapper.paraPagamentoDTO(pagamentoAtualizado));
    }
}
