package Model;

import java.time.LocalDate;

public class ClienteMensalista extends Cliente {

    private LocalDate prazoSaida;
    private double taxaMensal;
    private LocalDate dataRegistro;

    // Construtor
    public ClienteMensalista(int idCliente, String nome, String documento, String telefone, String email, LocalDate prazoSaida, double taxaMensal, LocalDate dataRegistro) {
        super(idCliente, true, nome, documento, telefone, email); // status sempre inicia como ativo
        this.prazoSaida = prazoSaida;
        this.taxaMensal = taxaMensal;
        this.dataRegistro = dataRegistro;
    }

    // Getters e Setters
    public LocalDate getPrazoSaida() {
        return prazoSaida;
    }

    public void setPrazoSaida(LocalDate prazoSaida) {
        this.prazoSaida = prazoSaida;
    }

    public double getTaxaMensal() {
        return taxaMensal;
    }

    public void setTaxaMensal(double taxaMensal) {
        this.taxaMensal = taxaMensal;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    @Override
    public String toString() {
        return "ClienteMensalista{" +
                "prazoSaida=" + prazoSaida +
                ", taxaMensal=" + taxaMensal +
                ", dataRegistro=" + dataRegistro +
                ", idCliente=" + getIdCliente() +
                ", nome='" + getNome() + '\'' +
                ", documento='" + getDocumento() + '\'' +
                ", status=" + (isStatus() ? "Ativo" : "Inativo") +
                '}';
    }
}
