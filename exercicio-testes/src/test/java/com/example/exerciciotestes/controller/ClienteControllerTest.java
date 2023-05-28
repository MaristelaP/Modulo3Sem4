package com.example.exerciciotestes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void saveCliente() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/clientes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                             """
                                {
                                    "nome": "Maristela",
                                    "saldo": 800.00
                                }
                             """
                        )
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().json(
                        """
                                {
                                    "id": 1,
                                    "nome": "Maristela",
                                    "saldo": 800.00
                                }
                                """
                ));
    }

    @Test
    void getAllCliente() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/clientes")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.content().json(
                """
                            []
                          """
        ));
    }


}