package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private int idCliente;
    private boolean status; // Indica se o cliente está ativo ou inativo
    private List<Veiculo> veiculos; // Lista de veículos associados ao cliente
    private List<Pagamento> pagamentos; // Histórico de pagamentos do cliente
    private Vaga vaga; // Espaço de estacionamento alocado para o cliente
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    // Construtor do Cliente
    public Cliente(int idCliente, boolean status, String nome, String documento, String telefone, String email) {
        super(nome, documento, telefone, email);
        this.idCliente = idCliente;
        this.status = status;
        this.veiculos = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }

    // Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public boolean isStatus() {
        return status;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void registrarSaida() {
        this.horaSaida = LocalDateTime.now();
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }


    // Métodos de manipulação de veículos e pagamentos
    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void adicionarPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    // Calcula o valor total com base na duração do estacionamento e no valor por hora do espaço
    public double calcularValorTotal() {
        if (vaga != null && horaEntrada != null && horaSaida != null) {
            Duration duracao = Duration.between(horaEntrada, horaSaida);
            long horas = duracao.toHours();
            if (horas < 1) {
                horas = 1; // Cobra pelo menos uma hora
            }
            return horas * vaga.getValorPorHora();
        }
        return 0.0;
    }

    // Calcula o valor com multa, se aplicável para ClienteMensalista
    public double calcularValorComMulta() {
        double valorTotal = calcularValorTotal();
        if (this instanceof ClienteMensalista) {
            ClienteMensalista clienteMensal = (ClienteMensalista) this;
            valorTotal += clienteMensal.getMulta();
        }
        return valorTotal;
    }

    // Override do método toString para exibir as informações do cliente
    @Override
    public String toString() {
        return "Cliente{" +
               "idCliente=" + idCliente +
               ", nome=" + getNome() +
               ", status=" + (status ? "Ativo" : "Inativo") +
               ", veículos=" + veiculos.size() +
               ", espaçoEstacionamento=" + (vaga != null ? vaga.getIdentificador() : "Não alocado") +
               '}';
    }
}
