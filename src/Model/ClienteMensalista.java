package Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalDate;

public class ClienteMensalista extends Cliente {
    private LocalDate prazoSaida;
    private double taxaMensal;
    private LocalDate dataRegistro;

    public ClienteMensalista(int idCliente, String nome, String documento, String telefone, String email, LocalDate prazoSaida, double taxaMensal, LocalDate dataRegistro) {
        super(idCliente, nome, documento, telefone, email);
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
}
