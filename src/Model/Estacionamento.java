package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private List<Cliente> clientesEstacionados; // Lista de clientes atualmente estacionados
    private List<Cliente> historicoClientes;    // Histórico de todos os clientes que já passaram pelo estacionamento
    private List<Vaga> vagas;                   // Lista de vagas disponíveis no estacionamento

    public Estacionamento() {
        this.clientesEstacionados = new ArrayList<>();
        this.historicoClientes = new ArrayList<>();
        this.vagas = new ArrayList<>();

        // Adiciona algumas vagas para exemplo
        vagas.add(new Vaga("A1", 5.0, false, false, null, null));
        vagas.add(new Vaga("B2", 7.0, true, false, null, null));
        // Adicione mais vagas conforme necessário
    }

    // Método para buscar uma vaga pelo identificador
    public Vaga getEspacoPorIdentificador(String identificador) {
        for (Vaga vaga : vagas) {
            if (vaga.getIdentificador().equals(identificador)) {
                return vaga;
            }
        }
        return null; // Retorna null se nenhuma vaga com o identificador for encontrada
    }

    // Método para registrar entrada de um cliente
    public void registrarEntrada(Cliente cliente) {
        clientesEstacionados.add(cliente);
    }

    // Método para registrar a saída de um cliente e mover para o histórico
    public void registrarSaida(Cliente cliente) {
        cliente.registrarSaida();  // Registra a saída do cliente
        clientesEstacionados.remove(cliente);  // Remove da lista de clientes estacionados
        historicoClientes.add(cliente);  // Adiciona ao histórico de clientes
    }

    // Método para calcular o valor total dos carros que já saíram
    public double calcularValorTotalCarrosQueSairam() {
        double valorTotal = 0.0;
        for (Cliente cliente : historicoClientes) {
            valorTotal += cliente.calcularValorTotal();
        }
        return valorTotal;
    }

    // Método para calcular o valor total gerado pelo estacionamento (carros estacionados + carros que já saíram)
    public double calcularValorTotalDoEstacionamento() {
        double valorTotal = calcularValorTotalCarrosQueSairam();  // Valor total dos carros que saíram
        for (Cliente cliente : clientesEstacionados) {
            valorTotal += cliente.calcularValorTotal();  // Adiciona o valor estimado dos carros ainda estacionados
        }
        return valorTotal;
    }

    // Método para listar clientes que ainda estão estacionados
    public List<Cliente> getClientesEstacionados() {
        return clientesEstacionados;
    }

    // Método para listar clientes no histórico (que já saíram)
    public List<Cliente> getHistoricoClientes() {
        return historicoClientes;
    }

    // Método para listar todas as vagas do estacionamento
    public List<Vaga> getVagas() {
        return vagas;
    }
}
