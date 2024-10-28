package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private List<Cliente> clientesEstacionados;
    private List<Cliente> historicoClientes;
    private List<EspacoEstacionamento> espacos;

    public Estacionamento() {
        this.clientesEstacionados = new ArrayList<>();
        this.historicoClientes = new ArrayList<>();
        this.espacos = new ArrayList<>();
    }

    public void adicionarEspaco(EspacoEstacionamento espaco) {
        espacos.add(espaco);
    }

    public EspacoEstacionamento getEspacoPorIdentificador(String identificador) {
        for (EspacoEstacionamento espaco : espacos) {
            if (espaco.getIdentificador().equals(identificador)) {
                return espaco;
            }
        }
        return null;
    }

    public String obterEspacoCliente(int clienteId) {
        for (EspacoEstacionamento espaco : espacos) {
            if (espaco.getCliente() != null && espaco.getCliente().getIdCliente() == clienteId) {
                return espaco.getIdentificador();
            }
        }
        return null;
    }

    public void registrarEntrada(Cliente cliente, String espacoIdentificador) {
        EspacoEstacionamento espaco = getEspacoPorIdentificador(espacoIdentificador);
        if (espaco != null && espaco.ocupar(cliente)) {
            cliente.setEspacoEstacionamento(espaco);
            cliente.setHoraEntrada(LocalDateTime.now());
            clientesEstacionados.add(cliente);
        } else {
            System.out.println("Espaço não disponível ou não encontrado.");
        }
    }

    public void registrarSaida(Cliente cliente) {
        cliente.registrarSaida();
        EspacoEstacionamento espaco = cliente.getEspacoEstacionamento();
        if (espaco != null) {
            espaco.desocupar();
        }
        clientesEstacionados.remove(cliente);
        historicoClientes.add(cliente);
    }

    public double calcularValorTotalCarrosQueSairam() {
        double valorTotal = 0.0;
        for (Cliente cliente : historicoClientes) {
            valorTotal += cliente.calcularValorTotal();
        }
        return valorTotal;
    }

    public double calcularValorTotalDoEstacionamento() {
        double valorTotal = calcularValorTotalCarrosQueSairam();
        for (Cliente cliente : clientesEstacionados) {
            valorTotal += cliente.calcularValorTotal();
        }
        return valorTotal;
    }

    public List<Cliente> getClientesEstacionados() {
        return clientesEstacionados;
    }

    public List<Cliente> getHistoricoClientes() {
        return historicoClientes;
    }
}
