package br.com.on.fiap.adaptadores.pagamento.saida.api.mercadopago.integracao;

import br.com.on.fiap.adaptadores.pagamento.saida.api.mercadopago.dto.resposta.PagamentoRespostaIntegracaoDTO;
import br.com.on.fiap.adaptadores.pagamento.saida.api.mercadopago.dto.solicitacao.PagamentoSolicitacaoIntegracaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamentoClient", url = "${integracoes.pagamento.url}")
public interface IntegracaoPagamento {

    @PostMapping("/payments")
    ResponseEntity<PagamentoRespostaIntegracaoDTO> enviarPagamento(
            @RequestBody PagamentoSolicitacaoIntegracaoDTO pagamentoSolicitacaoIntegracaoDTO);
}
