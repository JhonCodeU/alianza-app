package com.boot.controller;

import com.boot.entity.Client;
import com.boot.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Endpoint /test accedido.");
        return "Funcionando";
    }

    @GetMapping
    public List<Client> getAllClients() {
        logger.info("Obteniendo todos los clientes.");
        return clientService.fetchAllClientes();
    }

    @GetMapping("/{sharedKey}")
    public ResponseEntity<Client> getClientBySharedKey(@PathVariable String sharedKey) {
        logger.info("Buscando cliente con sharedKey: {}", sharedKey);
        Optional<Client> cliente = clientService.fetchClienteBySharedKey(sharedKey);

        if (cliente.isPresent()) {
            logger.info("Cliente encontrado: {}", cliente.get());
        } else {
            logger.warn("Cliente con sharedKey {} no encontrado.", sharedKey);
        }
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        logger.info("Creando nuevo cliente: {}", client);
        Client newClient = clientService.createCliente(client);
        return ResponseEntity.ok(newClient);
    }

    @PutMapping("/{sharedKey}")
    public ResponseEntity<Client> updateClient(@PathVariable String sharedKey, @RequestBody Client cliente) {
        logger.info("Actualizando cliente con sharedKey: {}", sharedKey);
        Optional<Client> updatedClient = clientService.updateCliente(sharedKey, cliente);

        if (updatedClient.isPresent()) {
            logger.info("Cliente actualizado: {}", updatedClient.get());
        } else {
            logger.warn("Cliente con sharedKey {} no encontrado para actualización.", sharedKey);
        }
        return updatedClient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{sharedKey}")
    public ResponseEntity<Void> deleteClient(@PathVariable String sharedKey) {
        logger.info("Eliminando cliente con sharedKey: {}", sharedKey);
        boolean deleted = clientService.deleteCliente(sharedKey);

        if (deleted) {
            logger.info("Cliente eliminado con éxito.");
            return ResponseEntity.ok().build();
        } else {
            logger.warn("Cliente con sharedKey {} no encontrado para eliminación.", sharedKey);
            return ResponseEntity.notFound().build();
        }
    }
}