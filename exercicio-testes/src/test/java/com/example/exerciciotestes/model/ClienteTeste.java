package com.example.exerciciotestes.model;

import com.example.exerciciotestes.controller.request.ClienteRequest;
import com.example.exerciciotestes.repository.ClienteRepository;
import com.example.exerciciotestes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteTeste {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void buscaTodosClientes(){

        List<Cliente> listaClientesMock = List.of(
                new Cliente("Maristela", 200.00)
        );

        when(clienteRepository.findAll()).thenReturn(listaClientesMock);

        List<Cliente> clienteResposta = clienteService.buscaTodosClientes();

        assertNotNull(clienteResposta);
        assertEquals(listaClientesMock.get(0).getNomeCliente(), clienteResposta.get(0).getNomeCliente());
        verify(clienteRepository).findAll();

    }

    @Test
    void buscaClientePorID(){
        Cliente clienteMock =
                new Cliente(1L,"Maristela", 200.00);


        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));

        Cliente respotaClienteId = clienteService.buscaClientePorId(anyLong());

        assertNotNull(respotaClienteId);
        assertEquals(clienteMock.getId(), respotaClienteId.getId());
        verify(clienteRepository).findById(anyLong());

    }

    @Test
    void salvarCliente(){
        Cliente salvarClienteMock = new Cliente("Mari", 200.00);

        when(clienteRepository.save(any())).thenReturn(salvarClienteMock);

        Cliente respostaSalvar = clienteService.salvarCliente(
                new ClienteRequest("Mari", 200.00)
        );

        assertNotNull(respostaSalvar);
        assertEquals(salvarClienteMock.getNomeCliente(), respostaSalvar.getNomeCliente());
        verify(clienteRepository).save(any());
    }

    @Test
    void atualizarCliente(){
        Cliente atualizarClienteMock =
                new Cliente(1L, "MaristelaP",500.00);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(atualizarClienteMock));
        when(clienteRepository.save(any())).thenReturn(atualizarClienteMock);

        Cliente respostaAtualizarClienteMock = clienteService.atualizarCliente(1L,
                new ClienteRequest("MaristelaP", 500.00));

        assertNotNull(respostaAtualizarClienteMock);
        assertEquals(atualizarClienteMock.getNomeCliente(), respostaAtualizarClienteMock.getNomeCliente());
        verify(clienteRepository).findById(anyLong());
        verify(clienteRepository).save(any());

    }

    @Test
    void deletaClientePorId(){

        Cliente clienteMock = new Cliente(1L, "Alguém", 500.00);

        Boolean respostaDeleta = clienteService.deletaClientePorId(1L);

        assertNotNull(respostaDeleta);
        assertTrue(respostaDeleta);
    }


}