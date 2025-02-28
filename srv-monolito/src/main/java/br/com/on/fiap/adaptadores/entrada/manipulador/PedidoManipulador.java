package br.com.on.fiap.adaptadores.entrada.manipulador;

import br.com.on.fiap.adaptadores.entrada.manipulador.dto.filtro.PedidoFiltroDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.mapeador.PagamentoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.manipulador.mapeador.PedidoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.manipulador.mapeador.filtro.PedidoFiltroEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.manipulador.swagger.PedidoControladorSwagger;
import br.com.on.fiap.hexagono.entities.entidades.Pagamento;
import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.entities.entidades.PedidoFiltro;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.AtualizaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto.ValidaProdutosDoPedidoCasoDeUso;
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

    private final InserePedidoCasoDeUso inserePedidoCasoDeUso;
    private final BuscaPedidosCasoDeUso buscaPedidosCasoDeUso;
    private final DetalhaPedidoCasoDeUso detalhaPedidoCasoDeUso;
    private final ValidaProdutosDoPedidoCasoDeUso validaProdutosDoPedidoPortaEntrada;
    private final AtualizaPedidoCasoDeUso atualizaPedidoCasoDeUso;

    private final PedidoEntradaMapeador pedidoEntradaMapeador;
    private final PedidoFiltroEntradaMapeador pedidoFiltroEntradaMapeador;
    private final PagamentoEntradaMapeador pagamentoEntradaMapeador;

    public PedidoManipulador(
            InserePedidoCasoDeUso inserePedidoCasoDeUso,
            BuscaPedidosCasoDeUso buscaPedidosCasoDeUso,
            DetalhaPedidoCasoDeUso detalhaPedidoCasoDeUso,
            ValidaProdutosDoPedidoCasoDeUso validaProdutosDoPedidoPortaEntrada,
            AtualizaPedidoCasoDeUso atualizaPedidoCasoDeUso,
            PedidoEntradaMapeador pedidoEntradaMapeador,
            PedidoFiltroEntradaMapeador pedidoFiltroEntradaMapeador,
            PagamentoEntradaMapeador pagamentoEntradaMapeador) {
        this.inserePedidoCasoDeUso = inserePedidoCasoDeUso;
        this.buscaPedidosCasoDeUso = buscaPedidosCasoDeUso;
        this.detalhaPedidoCasoDeUso = detalhaPedidoCasoDeUso;
        this.validaProdutosDoPedidoPortaEntrada = validaProdutosDoPedidoPortaEntrada;
        this.atualizaPedidoCasoDeUso = atualizaPedidoCasoDeUso;
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
        Pedido pedidoPersistido = inserePedidoCasoDeUso.inserir(pedidoComProdutosValidos, pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoEntradaMapeador.paraPedidoDTO(pedidoPersistido));
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<PedidoRespostaDTO>> buscaPedidosPaginado(
            @ParameterObject PedidoFiltroDTO pedidoFiltroDTO, Pageable pageable) {
        PedidoFiltro pedidoFiltro = pedidoFiltroEntradaMapeador.paraPedidoFiltro(pedidoFiltroDTO);
        Page<PedidoRespostaDTO> pedidos = buscaPedidosCasoDeUso
                .buscarPedidosComFiltro(pedidoFiltro, pageable)
                .map(pedidoEntradaMapeador::paraPedidoDTO);
        return ResponseEntity.ok().body(new PagedModel<>(pedidos));
    }

    @Override
    @GetMapping("/{protocolo}/detalhar")
    public ResponseEntity<PedidoDetalhadoRespostaDTO> detalhaPedido(@PathVariable("protocolo") String protocolo) {
        Pedido pedidoDetalhado = detalhaPedidoCasoDeUso.detalhaPedido(protocolo);
        PedidoDetalhadoRespostaDTO pedidoDetalhadoRespostaDTO =
                pedidoEntradaMapeador.paraPedidoDetalheDTO(pedidoDetalhado);
        return ResponseEntity.ok(pedidoDetalhadoRespostaDTO);
    }

    @Override
    @PutMapping("/{protocolo}")
    public ResponseEntity<PedidoRespostaDTO> atualizarPedido(@PathVariable("protocolo") String protocolo) {
        Pedido pedido = atualizaPedidoCasoDeUso.atualizarPedido(protocolo);
        return ResponseEntity.ok(pedidoEntradaMapeador.paraPedidoDTO(pedido));
    }
}
