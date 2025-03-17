package br.com.on.fiap.adapter.output.api.mercadopago.client;

import br.com.on.fiap.adapter.output.api.mercadopago.dto.PagamentoRespostaIntegracaoDTO;
import br.com.on.fiap.adapter.output.api.mercadopago.dto.PagamentoSolicitacaoIntegracaoDTO;
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
