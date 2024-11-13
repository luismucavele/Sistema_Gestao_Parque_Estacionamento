package Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteMensalista extends Cliente {
    private LocalDate prazoSaida;
    private double taxaMensal;
    private LocalDate dataRegistro;
    private Veiculo veiculo;  // Veículo associado
    private boolean estacionado;

    // Construtor completo com Veiculo
    public ClienteMensalista(int idCliente, String nome, String documento, String telefone, String email, LocalDate prazoSaida, double taxaMensal, LocalDate dataRegistro, Veiculo veiculo) {
        super(idCliente, true, nome, documento, telefone, email);
        this.prazoSaida = prazoSaida;
        this.taxaMensal = taxaMensal;
        this.dataRegistro = dataRegistro;
        this.veiculo = veiculo;
        this.estacionado = false;
    }

    // Método para verificar se o cliente ultrapassou o prazo
    public boolean isPrazoUltrapassado(LocalDateTime horaSaida) {
        return horaSaida != null && prazoSaida.isBefore(horaSaida.toLocalDate());
    }

    // Método para calcular a multa em caso de ultrapassagem do prazo
    public double getMulta() {
        if (getHoraSaida() != null && isPrazoUltrapassado(getHoraSaida())) {
            Duration diferenca = Duration.between(prazoSaida.atStartOfDay(), getHoraSaida());
            long horasExtras = diferenca.toHours();
            return horasExtras * 20.0; // Multa de 20 unidades monetárias por hora extra
        }
        return 0.0;
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

    public Cliente getCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
