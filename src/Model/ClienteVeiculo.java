package model;

import java.time.LocalDateTime;

public class ClienteVeiculo {
    private int idCliente;
    private String nome;
    private String residencia;
    private String contacto;
    private String matricula;  // Placa do veículo
    private String corCarro;
    private String tipoPagamento; // Tipo de pagamento (mensal, temporário, etc.)
    private double valorPorHora; // Valor por hora do estacionamento
    private LocalDateTime dataHoraEntrada;
    private boolean estacionado;

    // Construtor
    public ClienteVeiculo(int idCliente, String nome, String residencia, String contacto, String matricula, 
                          String corCarro, String tipoPagamento, double valorPorHora) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.residencia = residencia;
        this.contacto = contacto;
        this.matricula = matricula;
        this.corCarro = corCarro;
        this.tipoPagamento = tipoPagamento;
        this.valorPorHora = valorPorHora;
        this.estacionado = false; // Por padrão, o cliente não está estacionado
    }

    // Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorCarro() {
        return corCarro;
    }

    public void setCorCarro(String corCarro) {
        this.corCarro = corCarro;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
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

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public boolean isEstacionado() {
        return estacionado;
    }

    public void setEstacionado(boolean estacionado) {
        this.estacionado = estacionado;
    }
}