package vendas.clientes;

import java.util.HashMap;
import java.util.Map;

    // Criamos um Map(array que guarda dados em formato key:value como se fosse o "banco de dados" dos clientes cadastrados.

public class ClienteService implements IClienteService {
    private final Map<Integer, Cliente> clientes;

    // Construtor
    public ClienteService() {
        this.clientes = new HashMap<>();
    }


    @Override
    public void cadastrarCliente(Cliente cliente) {
        this.clientes.put(cliente.getId(), cliente);
    }

    @Override
    public Cliente consultarCliente(int id) {
        return this.clientes.get(id);
    }

    @Override
    public Cliente[] listarClientes() {
        Cliente[] clientes = new Cliente[this.clientes.size()];
        return this.clientes.values().toArray(clientes);
    }
}
