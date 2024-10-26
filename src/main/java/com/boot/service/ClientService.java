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
        clientes.add(new Client("jgutierrez", "Javier Gutierrez", "jgutierrez@example.com", "555-1234", LocalDate.now(), null, LocalDate.now()));
        clientes.add(new Client("jgonzalez", "Javier Gonzalez", "jgonzalez@example.com", "555-5678", LocalDate.now(), null, LocalDate.now()));
        clientes.add(new Client("jlopez", "Javier Lopez", "jlopez@example.com", "555-9012", LocalDate.now(), null, LocalDate.now()));
        clientes.add(new Client("egarcia", "Estefania Garcia", "egarcia@example.com", "555-3456", LocalDate.now(), null, LocalDate.now()));
        clientes.add(new Client("mlopez", "Maria Lopez", "mlopez@example.com", "555-7890", LocalDate.now(), null, LocalDate.now()));
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
