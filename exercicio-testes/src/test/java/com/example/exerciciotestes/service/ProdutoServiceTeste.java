package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.ClienteRequest;
import com.example.exerciciotestes.controller.request.ProdutoRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ProdutoServiceTeste {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void buscaTodosProdutos() {
        List<Produto> listaProdutosMock = List.of(
                new Produto("caneta", 2.00)
        );

        when(produtoRepository.findAll()).thenReturn(listaProdutosMock);

        List<Produto> produtoResposta = produtoService.buscaTodosProdutos();

        assertNotNull(produtoResposta);
        assertEquals(listaProdutosMock.get(0).getNomeProduto(), produtoResposta.get(0).getNomeProduto());
        verify(produtoRepository).findAll();
    }

    @Test
    void buscaProdutoPorId() {
        Produto produtoMock =
                new Produto(1L,"caneta", 2.00);


        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produtoMock));

        Produto respotaProdutoMockId = produtoService.buscaProdutoPorId(anyLong());

        assertNotNull(respotaProdutoMockId);
        assertEquals(produtoMock.getId(), respotaProdutoMockId.getId());
        verify(produtoRepository).findById(anyLong());
    }

    @Test
    void salvarProduto(){
        Produto salvarProdutoMock = new Produto("caderno", 20.00);

        when(produtoRepository.save(any())).thenReturn(salvarProdutoMock);

        Produto respostaSalvar = produtoService.salvarProduto(
                new ProdutoRequest("caderno", 20.00)
        );

        assertNotNull(respostaSalvar);
        assertEquals(salvarProdutoMock.getNomeProduto(), respostaSalvar.getNomeProduto());
        assertEquals(salvarProdutoMock.getValorProduto(), respostaSalvar.getValorProduto());
        verify(produtoRepository).save(any());
    }

    @Test
    void atualizarProduto(){
        Produto atualizarProdutoMock =
                new Produto(1L, "caneta Bic",5.00);

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(atualizarProdutoMock));
        when(produtoRepository.save(any())).thenReturn(atualizarProdutoMock);

        Produto respostaAtualizarClienteMock = produtoService.atualizarProduto(1L,
                new ProdutoRequest("caneta Bic", 5.00));

        assertNotNull(respostaAtualizarClienteMock);
        assertEquals(atualizarProdutoMock.getNomeProduto(), respostaAtualizarClienteMock.getNomeProduto());
        assertEquals(atualizarProdutoMock.getValorProduto(), respostaAtualizarClienteMock.getValorProduto());
        verify(produtoRepository).findById(anyLong());
        verify(produtoRepository).save(any());

    }

    @Test
    void deletaProdutoPorId(){
        Boolean respostaDeleta = produtoService.deletaProdutoPorId(1L);

        assertNotNull(respostaDeleta);
        assertTrue(respostaDeleta);
    }
}