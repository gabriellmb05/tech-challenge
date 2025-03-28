package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.entrada.PedidoRequest;
import br.com.on.fiap.adapter.input.dto.filtro.PedidoFiltroRequest;
import br.com.on.fiap.adapter.input.dto.resposta.PaginacaoRespostaInfo;
import br.com.on.fiap.adapter.input.swagger.PedidoApiSwagger;
import br.com.on.fiap.core.adapter.controller.PedidoController;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.PedidoDetalhadoResposta;
import br.com.on.fiap.core.application.dto.resposta.PedidoResposta;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedidos")
public class PedidoApi implements PedidoApiSwagger {

    private final PedidoController pedidoController;

    public PedidoApi(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    @Override
    @PostMapping
    public ResponseEntity<PedidoResposta> inserePedido(@Valid @RequestBody PedidoRequest pedidoSolicitacaoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoController.inserePedido(pedidoSolicitacaoDTO));
    }

    @Override
    @GetMapping
    public ResponseEntity<PaginaResposta<PedidoResposta>> listarPedidoComFiltro(
            @ParameterObject PedidoFiltroRequest pedidoFiltroRequest, Pageable pageable) {
        PaginacaoResposta paginacaoResposta = PaginacaoRespostaInfo.from(pageable);
        PaginaResposta<PedidoResposta> pedidoPaginaResposta =
                pedidoController.listarPedidoComFiltro(pedidoFiltroRequest, paginacaoResposta);
        return ResponseEntity.ok().body(pedidoPaginaResposta);
    }

    @Override
    @GetMapping(value = "/ordenar")
    public ResponseEntity<PaginaResposta<PedidoResposta>> listarPedido(Pageable pageable) {
        PaginacaoResposta paginacaoResposta = PaginacaoRespostaInfo.from(pageable);
        PaginaResposta<PedidoResposta> pedidoPaginaResposta = pedidoController.listarPedidoComFiltro(paginacaoResposta);
        return ResponseEntity.ok().body(pedidoPaginaResposta);
    }

    @Override
    @GetMapping("/{protocolo}/detalhar")
    public ResponseEntity<PedidoDetalhadoResposta> detalhaPedido(@PathVariable("protocolo") String protocolo) {
        return ResponseEntity.ok(pedidoController.detalhaPedido(protocolo));
    }

    @Override
    @PutMapping("/{protocolo}")
    public ResponseEntity<PedidoResposta> atualizarPedido(@PathVariable("protocolo") String protocolo) {
        return ResponseEntity.ok(pedidoController.atualizarPedido(protocolo));
    }
}
