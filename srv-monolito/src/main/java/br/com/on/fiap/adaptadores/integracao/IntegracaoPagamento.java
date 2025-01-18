package br.com.on.fiap.adaptadores.integracao;

import br.com.on.fiap.adaptadores.integracao.solicitacao.PagamentoSolicitacaoIntegracaoDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamentoClient", url = "${pagamento.service.url}/${pagamento.service.path}")
public interface PagamentoCliente {

    @PostMapping("/pagamentos")
    ResponseEntity<Void> enviarPagamento(@RequestBody PagamentoSolicitacaoIntegracaoDTO pagamentoSolicitacaoIntegracaoDTO);

}
