package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

    private int idCliente;
    private boolean status;
    private List<Veiculo> veiculos;
    private List<Pagamento> pagamentos;
    private EspacoEstacionamento espacoEstacionamento;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public Cliente(int idCliente, boolean status, String nome, String documento, String telefone, String email) {
        super(nome, documento, telefone, email);
        this.idCliente = idCliente;
        this.status = status;
        this.veiculos = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }

    public boolean isStatus() {
        return status;
    }

    public int getIdCliente() {
        return idCliente;
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

    public EspacoEstacionamento getEspacoEstacionamento() {
        return espacoEstacionamento;
    }

    public void setEspacoEstacionamento(EspacoEstacionamento espacoEstacionamento) {
        this.espacoEstacionamento = espacoEstacionamento;
    }

    public double calcularValorTotal() {
        if (espacoEstacionamento != null && horaEntrada != null && horaSaida != null) {
            Duration duracao = Duration.between(horaEntrada, horaSaida);
            long horas = duracao.toHours();
            if (horas < 1) {
                horas = 1;
            }
            return horas * espacoEstacionamento.getValorPorHora();
        }
        return 0.0;
    }
}
