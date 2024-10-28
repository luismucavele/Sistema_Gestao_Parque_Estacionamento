package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class EspacoEstacionamento {
    private String identificador;
    private double valorPorHora;
    private boolean isVip;
    private boolean ocupado;
    private Cliente cliente;
    private LocalDateTime horaEntrada;

    public EspacoEstacionamento(String identificador, double valorPorHora, boolean isVip) {
        this.identificador = identificador;
        this.valorPorHora = valorPorHora;
        this.isVip = isVip;
        this.ocupado = false;
    }

    public String getIdentificador() {
        return identificador;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public boolean isVip() {
        return isVip;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public boolean ocupar(Cliente cliente) {
        if (!this.ocupado) {
            this.ocupado = true;
            this.cliente = cliente;
            this.horaEntrada = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public void desocupar() {
        this.ocupado = false;
        this.cliente = null;
        this.horaEntrada = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double calcularValorTotal() {
        if (this.cliente == null || !this.ocupado || this.horaEntrada == null) {
            return 0.0;
        }
        Duration duracao = Duration.between(this.horaEntrada, LocalDateTime.now());
        long horas = duracao.toHours();
        if (horas < 1) {
            horas = 1;
        }
        return horas * this.valorPorHora;
    }
}
