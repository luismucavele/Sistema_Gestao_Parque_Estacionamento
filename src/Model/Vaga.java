package Model;

import java.time.LocalDateTime;

public class Vaga {

    private String identificador;
    private double valorPorHora;
    private boolean isVip;
    private boolean ocupado;
    private Cliente cliente;
    private LocalDateTime horaEntrada;

    public Vaga(String identificador, double valorPorHora, boolean isVip, boolean ocupado, Cliente cliente, LocalDateTime horaEntrada) {
        this.identificador = identificador;
        this.valorPorHora = valorPorHora;
        this.isVip = isVip;
        this.ocupado = ocupado;
        this.cliente = cliente;
        this.horaEntrada = horaEntrada;
    }

    // Getters e Setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean isVip) {
        this.isVip = isVip;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    // Métodos adicionais
    public double calcularValorEstacionamento(LocalDateTime horaSaida) {
        long horasEstacionadas = java.time.Duration.between(horaEntrada, horaSaida).toHours();
        return horasEstacionadas * valorPorHora;
    }

    public void ocuparVaga(Cliente cliente, LocalDateTime horaEntrada) {
        this.ocupado = false;
        this.cliente = cliente;
        this.horaEntrada = horaEntrada;
    }

    public void liberarVaga() {
        this.ocupado = false;
        this.cliente = null;
        this.horaEntrada = null;
    }

    @Override
    public String toString() {
        return "Vaga{"
                + "identificador='" + identificador + '\''
                + ", valorPorHora=" + valorPorHora
                + ", isVip=" + isVip
                + ", ocupado=" + ocupado
                + ", cliente=" + cliente
                + ", horaEntrada=" + horaEntrada
                + '}';
    }
}
