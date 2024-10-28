package Model;

import java.time.LocalDateTime;

public class ClienteVaga {
    // Composição: Cliente, Veiculo e Vaga
    private Cliente cliente;
    private Veiculo veiculo;
    private Vaga vaga;

    // Atributos específicos de ClienteVaga
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    // Construtor
    public ClienteVaga(Cliente cliente, Veiculo veiculo, Vaga vaga, LocalDateTime horaEntrada, LocalDateTime horaSaida) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    // Getters e Setters para Cliente, Veiculo e Vaga (com composição)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    // Getters e Setters para horaEntrada e horaSaida
    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    // Método para calcular o total a pagar com base no valor por hora da vaga
    public double calcularTotal() {
        if (horaEntrada != null && horaSaida != null && vaga != null) {
            long diffMillis = java.time.Duration.between(horaEntrada, horaSaida).toMillis();
            double diffHoras = diffMillis / (1000.0 * 60 * 60);
            return diffHoras * vaga.getValorPorHora();
        }
        return 0.0;
    }
}
