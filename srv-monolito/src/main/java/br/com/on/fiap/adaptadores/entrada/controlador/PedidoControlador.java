package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro.PedidoFiltroDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.PagamentoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.PedidoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.filtro.PedidoFiltroEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.swagger.PedidoControladorSwagger;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import br.com.on.fiap.hexagono.portas.entrada.pedido.BuscaPedidosPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.DetalhaPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.InserePedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.produto.ValidaProdutosDoPedidoPortaEntrada;
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
public class PedidoControlador implements PedidoControladorSwagger {

    private final InserePedidoPortaEntrada inserePedidoPortaEntrada;
    private final BuscaPedidosPortaEntrada buscaPedidosPortaEntrada;
    private final DetalhaPedidoPortaEntrada detalhaPedidoPortaEntrada;
    private final ValidaProdutosDoPedidoPortaEntrada validaProdutosDoPedidoPortaEntrada;

    private final PedidoEntradaMapeador pedidoEntradaMapeador;
    private final PedidoFiltroEntradaMapeador pedidoFiltroEntradaMapeador;
    private final PagamentoEntradaMapeador pagamentoEntradaMapeador;

    public PedidoControlador(
            InserePedidoPortaEntrada inserePedidoPortaEntrada,
            BuscaPedidosPortaEntrada buscaPedidosPortaEntrada,
            DetalhaPedidoPortaEntrada detalhaPedidoPortaEntrada,
            ValidaProdutosDoPedidoPortaEntrada validaProdutosDoPedidoPortaEntrada,
            PedidoEntradaMapeador pedidoEntradaMapeador,
            PedidoFiltroEntradaMapeador pedidoFiltroEntradaMapeador,
            PagamentoEntradaMapeador pagamentoEntradaMapeador) {
        this.inserePedidoPortaEntrada = inserePedidoPortaEntrada;
        this.buscaPedidosPortaEntrada = buscaPedidosPortaEntrada;
        this.detalhaPedidoPortaEntrada = detalhaPedidoPortaEntrada;
        this.validaProdutosDoPedidoPortaEntrada = validaProdutosDoPedidoPortaEntrada;
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
        Pedido pedidoPersistido = inserePedidoPortaEntrada.inserir(pedidoComProdutosValidos, pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoEntradaMapeador.paraPedidoDTO(pedidoPersistido));
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<PedidoRespostaDTO>> buscaPedidosPaginado(
            @ParameterObject PedidoFiltroDTO pedidoFiltroDTO, Pageable pageable) {
        PedidoFiltro pedidoFiltro = pedidoFiltroEntradaMapeador.paraPedidoFiltro(pedidoFiltroDTO);
        Page<PedidoRespostaDTO> pedidos = buscaPedidosPortaEntrada
                .buscarPedidosComFiltro(pedidoFiltro, pageable)
                .map(pedidoEntradaMapeador::paraPedidoDTO);
        return ResponseEntity.ok().body(new PagedModel<>(pedidos));
    }

    @Override
    @GetMapping("/{protocolo}/detalhar")
    public ResponseEntity<PedidoDetalhadoRespostaDTO> detalhaPedido(@PathVariable("protocolo") String protocolo) {
        Pedido pedidoDetalhado = detalhaPedidoPortaEntrada.detalhaPedido(protocolo);
        PedidoDetalhadoRespostaDTO pedidoDetalhadoRespostaDTO =
                pedidoEntradaMapeador.paraPedidoDetalheDTO(pedidoDetalhado);
        return ResponseEntity.ok(pedidoDetalhadoRespostaDTO);
    }
}
