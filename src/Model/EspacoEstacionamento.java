package Model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class EspacoEstacionamento implements Serializable {
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

    // Verifica se o espaço está disponível antes de ocupar
    public boolean ocupar(Cliente cliente) {
        if (!this.ocupado) {
            this.ocupado = true;
            this.cliente = cliente;
            this.horaEntrada = LocalDateTime.now();
            return true;
        }
        return false;
    }

    // Desocupa o espaço e limpa o cliente associado
    public void desocupar() {
        this.ocupado = false;
        this.cliente = null;
        this.horaEntrada = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // Calcula o valor total a ser cobrado baseado no tempo de permanência
    public double calcularValorTotal() {
        if (this.cliente == null || !this.ocupado || this.horaEntrada == null) {
            return 0.0;
        }
        Duration duracao = Duration.between(this.horaEntrada, LocalDateTime.now());
        long horas = duracao.toHours();
        if (horas < 1) {
            horas = 1; // Cobrar pelo menos uma hora
        }
        return horas * this.valorPorHora;
    }
}
