package br.com.on.fiap.infraestrutura.configuracao;

import br.com.on.fiap.dominio.adaptadores.ProdutoServicoImp;
import br.com.on.fiap.dominio.portas.interfaces.IProdutoServicoPorta;
import br.com.on.fiap.dominio.portas.repositorios.IProdutoRepositorioPorta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    IProdutoServicoPorta produtoServico(IProdutoRepositorioPorta produtoRepositorioPorta){
        return new ProdutoServicoImp(produtoRepositorioPorta);
    }
}
