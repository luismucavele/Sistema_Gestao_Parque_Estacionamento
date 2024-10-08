package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Cliente extends Pessoa {
    private static int idCounter = 1;
    protected int idCliente;
    protected String matricula;
    protected String corCarro;
    protected double valorPorHora;
    protected String tipoPagamento;
    protected boolean estacionado;
    protected LocalDateTime dataHoraEntrada;
    protected LocalDateTime dataHoraSaida;
    protected int espacoEstacionado;

    public Cliente(String nome, String contacto, String residencia, String matricula, String corCarro, double valorPorHora, String tipoPagamento) {
        super(nome, contacto, residencia);
        this.idCliente = idCounter++;
        this.matricula = matricula;
        this.corCarro = corCarro;
        this.valorPorHora = valorPorHora;
        this.tipoPagamento = tipoPagamento;
        this.estacionado = true;
        this.dataHoraEntrada = LocalDateTime.now();
    }

    // Getters e setters
    public int getIdCliente() {
        return idCliente;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCorCarro() {
        return corCarro;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public boolean isEstacionado() {
        return estacionado;
    }

    public void setEstacionado(boolean estacionado) {
        this.estacionado = estacionado;
    }

    public int getEspacoEstacionado() {
        return espacoEstacionado;
    }

    public void setEspacoEstacionado(int espacoEstacionado) {
        this.espacoEstacionado = espacoEstacionado;
    }

    public void registrarSaida() {
        if (estacionado) {
            this.dataHoraSaida = LocalDateTime.now();
            this.estacionado = false;
        } else {
            System.out.println("O veículo já foi removido.");
        }
    }

    // Método para calcular o valor total a ser pago (clientes temporários)
    public double calcularValorTotal() {
        if (dataHoraSaida == null) {
            dataHoraSaida = LocalDateTime.now();
        }

        Duration duracao = Duration.between(dataHoraEntrada, dataHoraSaida);
        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();

        if (minutos > 0) {
            horas++;
        }

        return horas * valorPorHora;
    }

    // Método para exibir detalhes
    @Override
    public String exibirDetalhes() {
        return String.format(
            "ID Cliente: %d\nNome: %s\nContacto: %s\nResidência: %s\nMatrícula: %s\nCor do Carro: %s\nValor por Hora: %.2f\nTipo de Pagamento: %s\nEstacionado: %s\nHora de Entrada: %s\n",
            idCliente, getNome(), getContacto(), getResidencia(), matricula, corCarro, valorPorHora, tipoPagamento, (estacionado ? "Sim" : "Não"), dataHoraEntrada.toString()
        );
    }
}
