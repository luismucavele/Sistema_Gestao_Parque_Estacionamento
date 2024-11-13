package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class ClienteVeiculo {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private boolean estacionado;
    private Vaga vaga;

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public ClienteVeiculo(Cliente cliente, Veiculo veiculo, LocalDateTime horaEntrada, Vaga espacoEstacionamento) {
    if (cliente == null || veiculo == null) {
        throw new IllegalArgumentException("Cliente e veículo não podem ser nulos");
    }
    if (horaEntrada.isAfter(LocalDateTime.now())) {
        throw new IllegalArgumentException("Hora de entrada não pode ser no futuro");
    }
    this.cliente = cliente;
    this.veiculo = veiculo;
    this.horaEntrada = horaEntrada;
    this.vaga = vaga;  // Associa o espaço de estacionamento
    this.estacionado = true;
}

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    // Getters e Setters
    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

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

    public boolean isEstacionado() {
        return estacionado;
    }

    public void setEstacionado(boolean estacionado) {
        this.estacionado = estacionado;
    }

    // Métodos úteis
    public String getInformacoes() {
        return "Cliente: " + cliente.getNome() + ", Veículo: " + veiculo.getPlaca()
                + " (" + veiculo.getModelo() + ", " + veiculo.getMarca() + ")";
    }

    public boolean isClienteAtivo() {
        return cliente.isStatus();
    }

    public long calcularDuracaoEstacionamento() {
        Duration duracao = Duration.between(horaEntrada, LocalDateTime.now());
        return duracao.toMinutes();
    }

    public boolean pertenceAoCliente(Veiculo veiculo) {
        return this.veiculo.equals(veiculo);
    }

    public void exibirDetalhes() {
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Veículo: " + veiculo.getPlaca());
    }

    @Override
    public String toString() {
        return "ClienteVeiculo{" + "cliente=" + cliente + ", veiculo=" + veiculo + '}';
    }

}