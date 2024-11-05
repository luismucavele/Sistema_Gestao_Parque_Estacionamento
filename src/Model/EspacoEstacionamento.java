package Model;

import java.time.LocalDateTime;

public class EspacoEstacionamento {
    private String identificador;
    private double valorPorHora;
    private boolean isVip;
    private boolean ocupado;
     private ClienteVeiculo clienteVeiculo;


    // Construtor
    public EspacoEstacionamento(String identificador, double valorPorHora, boolean isVip) {
        this.identificador = identificador;
        this.valorPorHora = valorPorHora;
        this.isVip = isVip;
        this.ocupado = false;
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

    public void ocupar() {
        this.ocupado = true;
    }

    public void liberar() {
        this.ocupado = false;
    }

    // Método para calcular o custo baseado nas horas ocupadas
    public double calcularCusto(double horas) {
        return horas * valorPorHora;
    }

    // Método para verificar se o espaço é compatível com o cliente VIP
    public boolean verificarCompatibilidadeVip(boolean clienteVip) {
        return !isVip || (isVip && clienteVip);
    }

    // Método para resetar as configurações do espaço de estacionamento
    public void resetarEspaco() {
        this.ocupado = false;
        this.valorPorHora = 0;
        this.isVip = false;
    }

    
        public boolean ocupar(ClienteVeiculo clienteVeiculo) {
        if (!ocupado) {
            this.clienteVeiculo = clienteVeiculo;
            this.ocupado = true;
            return true;
        }
        return false;
    }

    public void desocupar() {
        this.clienteVeiculo = null;
        this.ocupado = false;
    }

    public ClienteVeiculo getClienteVeiculo() {
        return clienteVeiculo;
    }
    @Override
    public String toString() {
        return "EspacoEstacionamento{" +
                "identificador='" + identificador + '\'' +
                ", valorPorHora=" + valorPorHora +
                ", isVip=" + isVip +
                ", ocupado=" + ocupado +
                '}';
    }



   

}
