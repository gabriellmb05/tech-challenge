package br.com.on.fiap.adaptadores.pedido.entrada;

import br.com.on.fiap.adaptadores.pagamento.entrada.mapeador.PagamentoEntradaMapeador;
import br.com.on.fiap.adaptadores.pedido.PedidoControladorSwagger;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.filtro.PedidoFiltroDTO;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.pedido.entrada.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.pedido.entrada.mapeador.PedidoEntradaMapeador;
import br.com.on.fiap.adaptadores.pedido.entrada.mapeador.PedidoFiltroEntradaMapeador;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.entity.PedidoFiltro;
import br.com.on.fiap.hexagono.application.usecase.pedido.base.PedidoAtualizaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pedido.base.PedidoBuscaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pedido.base.PedidoDetalhaUseCase;
import br.com.on.fiap.hexagono.application.usecase.pedido.base.PedidoInsereUseCase;
import br.com.on.fiap.hexagono.application.usecase.produto.base.ProdutoValidaPedidoUseCase;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedidos")
public class PedidoManipulador implements PedidoControladorSwagger {

    private final PedidoInsereUseCase pedidoInsereUseCase;
    private final PedidoBuscaUseCase pedidoBuscaUseCase;
    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final ProdutoValidaPedidoUseCase validaProdutosDoPedidoPortaEntrada;
    private final PedidoAtualizaUseCase pedidoAtualizaUseCase;

    private final PedidoEntradaMapeador pedidoEntradaMapeador;
    private final PedidoFiltroEntradaMapeador pedidoFiltroEntradaMapeador;
    private final PagamentoEntradaMapeador pagamentoEntradaMapeador;

    public PedidoManipulador(
            PedidoInsereUseCase pedidoInsereUseCase,
            PedidoBuscaUseCase pedidoBuscaUseCase,
            PedidoDetalhaUseCase pedidoDetalhaUseCase,
            ProdutoValidaPedidoUseCase validaProdutosDoPedidoPortaEntrada,
            PedidoAtualizaUseCase pedidoAtualizaUseCase,
            PedidoEntradaMapeador pedidoEntradaMapeador,
            PedidoFiltroEntradaMapeador pedidoFiltroEntradaMapeador,
            PagamentoEntradaMapeador pagamentoEntradaMapeador) {
        this.pedidoInsereUseCase = pedidoInsereUseCase;
        this.pedidoBuscaUseCase = pedidoBuscaUseCase;
        this.pedidoDetalhaUseCase = pedidoDetalhaUseCase;
        this.validaProdutosDoPedidoPortaEntrada = validaProdutosDoPedidoPortaEntrada;
        this.pedidoAtualizaUseCase = pedidoAtualizaUseCase;
        this.pedidoEntradaMapeador = pedidoEntradaMapeador;
        this.pedidoFiltroEntradaMapeador = pedidoFiltroEntradaMapeador;
        this.pagamentoEntradaMapeador = pagamentoEntradaMapeador;
    }

    @Override
    @PostMapping
    public ResponseEntity<PedidoRespostaDTO> inserePedido(
            @Valid @RequestBody PedidoSolicitacaoDTO pedidoSolicitacaoDTO) {
        Pedido pedidoParaValidar = pedidoEntradaMapeador.paraPedido(pedidoSolicitacaoDTO);
        Pedido pedidoComProdutosValidos = validaProdutosDoPedidoPortaEntrada.validarProdutosDoPedido(pedidoParaValidar);
        Pagamento pagamento = pagamentoEntradaMapeador.paraPagamento(pedidoSolicitacaoDTO.getPagamento());
        Pedido pedidoPersistido = pedidoInsereUseCase.inserir(pedidoComProdutosValidos, pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoEntradaMapeador.paraPedidoDTO(pedidoPersistido));
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<PedidoRespostaDTO>> buscaPedidosPaginado(
            @ParameterObject PedidoFiltroDTO pedidoFiltroDTO, Pageable pageable) {
        PedidoFiltro pedidoFiltro = pedidoFiltroEntradaMapeador.paraPedidoFiltro(pedidoFiltroDTO);
        Page<PedidoRespostaDTO> pedidos = pedidoBuscaUseCase
                .buscarPedidosComFiltro(pedidoFiltro, pageable)
                .map(pedidoEntradaMapeador::paraPedidoDTO);
        return ResponseEntity.ok().body(new PagedModel<>(pedidos));
    }

    @Override
    @GetMapping("/{protocolo}/detalhar")
    public ResponseEntity<PedidoDetalhadoRespostaDTO> detalhaPedido(@PathVariable("protocolo") String protocolo) {
        Pedido pedidoDetalhado = pedidoDetalhaUseCase.detalhaPedido(protocolo);
        PedidoDetalhadoRespostaDTO pedidoDetalhadoRespostaDTO =
                pedidoEntradaMapeador.paraPedidoDetalheDTO(pedidoDetalhado);
        return ResponseEntity.ok(pedidoDetalhadoRespostaDTO);
    }

    @Override
    @PutMapping("/{protocolo}")
    public ResponseEntity<PedidoRespostaDTO> atualizarPedido(@PathVariable("protocolo") String protocolo) {
        Pedido pedido = pedidoAtualizaUseCase.atualizarPedido(protocolo);
        return ResponseEntity.ok(pedidoEntradaMapeador.paraPedidoDTO(pedido));
    }
}
