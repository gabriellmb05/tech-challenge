package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.filter.PedidoFiltroDTO;
import br.com.on.fiap.adapter.input.dto.request.PedidoSolicitacaoDTO;
import br.com.on.fiap.adapter.input.dto.response.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adapter.input.dto.response.PedidoRespostaDTO;
import br.com.on.fiap.adapter.input.mapper.PagamentoInputMapper;
import br.com.on.fiap.adapter.input.mapper.PedidoFiltroInputMapper;
import br.com.on.fiap.adapter.input.mapper.PedidoInputMapper;
import br.com.on.fiap.adapter.input.swagger.PedidoApiSwagger;
import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.PedidoFiltro;
import br.com.on.fiap.core.usecase.pedido.PedidoAtualizaUseCase;
import br.com.on.fiap.core.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.usecase.pedido.PedidoInsereUseCase;
import br.com.on.fiap.core.usecase.pedido.PedidoListaUseCase;
import br.com.on.fiap.core.usecase.produto.ProdutoValidaPedidoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoApi implements PedidoApiSwagger {

    private final PedidoInsereUseCase pedidoInsereUseCase;
    private final PedidoListaUseCase pedidoListaUseCase;
    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final ProdutoValidaPedidoUseCase produtoValidaPedidoUseCase;
    private final PedidoAtualizaUseCase pedidoAtualizaUseCase;

    private final PedidoInputMapper pedidoInputMapper;
    private final PedidoFiltroInputMapper pedidoFiltroInputMapper;
    private final PagamentoInputMapper pagamentoInputMapper;

    @Override
    @PostMapping
    public ResponseEntity<PedidoRespostaDTO> inserePedido(
            @Valid @RequestBody PedidoSolicitacaoDTO pedidoSolicitacaoDTO) {
        Pedido pedidoParaValidar = pedidoInputMapper.paraPedido(pedidoSolicitacaoDTO);
        Pedido pedidoComProdutosValidos = produtoValidaPedidoUseCase.validarProdutosDoPedido(pedidoParaValidar);
        Pagamento pagamento = pagamentoInputMapper.paraPagamento(pedidoSolicitacaoDTO.getPagamento());
        Pedido pedidoPersistido = pedidoInsereUseCase.inserir(pedidoComProdutosValidos, pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoInputMapper.paraPedidoDTO(pedidoPersistido));
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<PedidoRespostaDTO>> buscaPedidosPaginado(
            @ParameterObject PedidoFiltroDTO pedidoFiltroDTO, Pageable pageable) {
        PedidoFiltro pedidoFiltro = pedidoFiltroInputMapper.paraPedidoFiltro(pedidoFiltroDTO);
        Page<PedidoRespostaDTO> pedidos = pedidoListaUseCase
                .buscarPedidosComFiltro(pedidoFiltro, pageable)
                .map(pedidoInputMapper::paraPedidoDTO);
        return ResponseEntity.ok().body(new PagedModel<>(pedidos));
    }

    @Override
    @GetMapping("/{protocolo}/detalhar")
    public ResponseEntity<PedidoDetalhadoRespostaDTO> detalhaPedido(@PathVariable("protocolo") String protocolo) {
        Pedido pedidoDetalhado = pedidoDetalhaUseCase.detalhaPedido(protocolo);
        PedidoDetalhadoRespostaDTO pedidoDetalhadoRespostaDTO = pedidoInputMapper.paraPedidoDetalheDTO(pedidoDetalhado);
        return ResponseEntity.ok(pedidoDetalhadoRespostaDTO);
    }

    @Override
    @PutMapping("/{protocolo}")
    public ResponseEntity<PedidoRespostaDTO> atualizarPedido(@PathVariable("protocolo") String protocolo) {
        Pedido pedido = pedidoAtualizaUseCase.atualizarPedido(protocolo);
        return ResponseEntity.ok(pedidoInputMapper.paraPedidoDTO(pedido));
    }
}
