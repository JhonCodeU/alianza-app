package com.boot.alianza.controller;

import com.boot.entity.Client;
import com.boot.service.ClientService;
import com.boot.controller.ClientController;
import main.AlianzaApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
@ContextConfiguration(classes = AlianzaApplication.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setSharedKey("key123");
        client.setBusinessId("John Doe");
    }

    @Test
    void testGetClientBySharedKeyFound() throws Exception {
        when(clientService.fetchClienteBySharedKey("key123")).thenReturn(Optional.of(client));

        mockMvc.perform(get("/api/v1/clientes/key123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.businessId").value("John Doe")); // Cambiado a $.businessId
    }

    @Test
    void testGetClientBySharedKeyNotFound() throws Exception {
        when(clientService.fetchClienteBySharedKey("key123")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/clientes/key123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateClient() throws Exception {
        when(clientService.createCliente(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sharedKey\": \"key123\", \"businessId\": \"John Doe\"}")) // Cambiado a "businessId"
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.businessId").value("John Doe")); // Cambiado a $.businessId
    }
}
