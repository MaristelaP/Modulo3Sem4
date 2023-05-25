package com.example.exerciciotestes.model;

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
                new Cliente("Maristela", 200.00);


        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));

        Cliente respotaClienteId = clienteService.buscaClientePorId(anyLong());

        assertNotNull(respotaClienteId);
        assertEquals(clienteMock.getId(), respotaClienteId.getId());
        verify(clienteRepository).findById(anyLong());

    }
}