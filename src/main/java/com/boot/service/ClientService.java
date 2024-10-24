package com.boot.service;

import com.boot.entity.Client;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final List<Client> clientes = new ArrayList<>();

    public ClientService() {
        // Cargar algunos clientes de ejemplo
        clientes.add(new Client("SH123", "123", "test@example.com", "555-1234", LocalDate.now(), null, LocalDate.now()));
    }

    // Obtener todos los clientes
    public List<Client> fetchAllClientes() {
        return clientes;
    }

    // Obtener un cliente por su sharedKey
    public Optional<Client> fetchClienteBySharedKey(String sharedKey) {
        return clientes.stream().filter(c -> c.getSharedKey().equals(sharedKey)).findFirst();
    }

    // Crear un nuevo cliente
    public Client createCliente(Client cliente) {
        cliente.setSharedKey(UUID.randomUUID().toString()); // Generar una clave única
        cliente.setDateCreate(LocalDate.now());
        clientes.add(cliente);
        return cliente;
    }

    // Actualizar cliente
    public Optional<Client> updateCliente(String sharedKey, Client updatedCliente) {
        return fetchClienteBySharedKey(sharedKey).map(c -> {
            c.setBusinessId(updatedCliente.getBusinessId());
            c.setEmail(updatedCliente.getEmail());
            c.setPhone(updatedCliente.getPhone());
            c.setDateStart(updatedCliente.getDateStart());
            c.setDateEnd(updatedCliente.getDateEnd());
            return c;
        });
    }

    // Eliminar cliente
    public boolean deleteCliente(String sharedKey) {
        return clientes.removeIf(c -> c.getSharedKey().equals(sharedKey));
    }
}
