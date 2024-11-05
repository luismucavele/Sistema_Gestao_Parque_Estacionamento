package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    // Método para calcular a multa caso o cliente ultrapasse o prazo de saída
    public double getMulta() {
        if (isPrazoUltrapassado(LocalDateTime.now())) {
            long diasAtraso = ChronoUnit.DAYS.between(prazoSaida, LocalDate.now());
            double taxaMultaDiaria = 0.1 * taxaMensal; // Exemplo: multa de 10% da taxa mensal por dia de atraso
            return diasAtraso * taxaMultaDiaria;
        }
        return 0.0;
    }

    // Verifica se o cliente ultrapassou o prazo de saída
    public boolean isPrazoUltrapassado(LocalDateTime horaSaida) {
        return horaSaida.toLocalDate().isAfter(prazoSaida);
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
