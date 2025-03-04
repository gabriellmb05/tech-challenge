package br.com.on.fiap.adapter.output.datasource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adapter.output.persistence.entity.ProdutoEntity;
import br.com.on.fiap.adapter.output.persistence.mapper.ProdutoSaidaMapeador;
import br.com.on.fiap.adapter.output.persistence.repository.ProdutoRepository;
import java.util.Optional;

import br.com.on.fiap.core.domain.entity.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoDataSourceImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoSaidaMapeador produtoSaidaMapeador;

    @InjectMocks
    private ProdutoDataSourceImpl produtoDataSource;

    @Test
    @DisplayName("Dado um produto existente, quando buscar por ID, então ele deve ser retornado")
    void dadoProdutoExistente_quandoBuscarPorId_entaoDeveSerRetornado() {
        Long id = 1L;
        ProdutoEntity produtoEntity = new ProdutoEntity();
        Produto produto = new Produto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntity));
        when(produtoSaidaMapeador.paraProduto(produtoEntity)).thenReturn(produto);

        Optional<Produto> resultado = produtoDataSource.buscaProdutoPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(produto, resultado.get());
        verify(produtoRepository).findById(id);
        verify(produtoSaidaMapeador).paraProduto(produtoEntity);
    }

    @Test
    @DisplayName("Dado um produto novo, quando salvar, então ele deve ser persistido")
    void dadoProdutoNovo_quandoSalvar_entaoDeveSerPersistido() {
        Produto produto = new Produto();
        ProdutoEntity produtoEntity = new ProdutoEntity();
        ProdutoEntity produtoPersistidoEntidade = new ProdutoEntity();
        Produto produtoPersistido = new Produto();
        when(produtoSaidaMapeador.paraEntidade(produto)).thenReturn(produtoEntity);
        when(produtoRepository.save(produtoEntity)).thenReturn(produtoPersistidoEntidade);
        when(produtoSaidaMapeador.paraProduto(produtoPersistidoEntidade)).thenReturn(produtoPersistido);

        Produto resultado = produtoDataSource.salvaProduto(produto);

        assertEquals(produtoPersistido, resultado);
        verify(produtoSaidaMapeador).paraEntidade(produto);
        verify(produtoRepository).save(produtoEntity);
        verify(produtoSaidaMapeador).paraProduto(produtoPersistidoEntidade);
    }

    @Test
    @DisplayName("Dado um produto existente, quando buscar por nome, então ele deve ser retornado")
    void dadoProdutoExistente_quandoBuscarPorNome_entaoDeveSerRetornado() {
        String nome = "Produto Teste";
        ProdutoEntity produtoEntity = new ProdutoEntity();
        Produto produto = new Produto();
        when(produtoRepository.findByNmNome(nome)).thenReturn(Optional.of(produtoEntity));
        when(produtoSaidaMapeador.paraProduto(produtoEntity)).thenReturn(produto);

        Optional<Produto> resultado = produtoDataSource.buscaProdutoPorNome(nome);

        assertTrue(resultado.isPresent());
        assertEquals(produto, resultado.get());
        verify(produtoRepository).findByNmNome(nome);
        verify(produtoSaidaMapeador).paraProduto(produtoEntity);
    }

    @Test
    @DisplayName("Dado um produto existente, quando deletar por ID, então ele deve ser removido")
    void dadoProdutoExistente_quandoDeletarPorId_entaoDeveSerRemovido() {
        Long id = 1L;

        produtoDataSource.deletaProdutoPorId(id);

        verify(produtoRepository).deleteById(id);
    }
}
