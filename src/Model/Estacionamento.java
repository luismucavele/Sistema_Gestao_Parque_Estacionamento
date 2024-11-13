package Model;

import Model.Cliente;
import Model.ClienteVeiculo;
import Model.Veiculo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List<ClienteVeiculo> clientesEstacionados;
    private List<ClienteVeiculo> historicoClientes;

    public Estacionamento() {
        this.clientesEstacionados = new ArrayList<>();
        this.historicoClientes = new ArrayList<>();
    }

   public void registrarEntrada(Cliente cliente, Veiculo veiculo, Vaga espaco) {
    ClienteVeiculo clienteVeiculo = new ClienteVeiculo(cliente, veiculo, LocalDateTime.now(), espaco);
    clientesEstacionados.add(clienteVeiculo);
}

    public void registrarSaida(Cliente cliente) {
        ClienteVeiculo clienteVeiculo = buscarClienteEstacionado(cliente);
        if (clienteVeiculo != null) {
            clienteVeiculo.setHoraSaida(LocalDateTime.now());  // Define a hora de saída
            clienteVeiculo.setEstacionado(false);
            historicoClientes.add(clienteVeiculo);
            clientesEstacionados.remove(clienteVeiculo);
        }
    }

    public ClienteVeiculo buscarClienteEstacionado(Cliente cliente) {
        for (ClienteVeiculo clienteVeiculo : clientesEstacionados) {
            if (clienteVeiculo.getCliente().equals(cliente) && clienteVeiculo.isEstacionado()) {
                return clienteVeiculo;
            }
        }
        return null;
    }

    public List<ClienteVeiculo> getClientesEstacionados() { return clientesEstacionados; }
    public List<ClienteVeiculo> getHistoricoClientes() { return historicoClientes; }
}
