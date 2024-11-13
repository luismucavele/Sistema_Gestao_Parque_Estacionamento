package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Vaga {

    private int idVaga;                     // ID da vaga no banco de dados
    private String identificador;           // Identificador único para a vaga
    private double valorPorHora;            // Valor cobrado por hora
    private boolean isVip;                  // Indicador se a vaga é VIP
    private boolean ocupado;                // Status de ocupação
    private LocalDateTime horaEntrada;      // Hora de entrada do veículo na vaga, se ocupada
    private int idParque;        // Identificador do espaço relacionado

    // Construtor completo
    public Vaga(int idVaga, String identificador, double valorPorHora, boolean isVip, boolean ocupado, LocalDateTime horaEntrada, int idParque) {
        this.idVaga = idVaga;
        this.identificador = identificador;
        this.valorPorHora = valorPorHora;
        this.isVip = isVip;
        this.ocupado = ocupado;
        this.horaEntrada = horaEntrada;
        this.idParque = idParque;
    }

    // Construtor básico (sem horaEntrada e idParqueRelacionado)
    public Vaga(String identificador, double valorPorHora, boolean isVip, boolean ocupado) {
        this.identificador = identificador;
        this.valorPorHora = valorPorHora;
        this.isVip = isVip;
        this.ocupado = ocupado;
    }

    // Getters e Setters
    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
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

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getIdParqueRelacionado() {
        return idParque;
    }

    public void setIdParqueRelacionado(int idParqueRelacionado) {
        this.idParque = idParqueRelacionado;
    }

    // Métodos de manipulação de status

    // Ocupa a vaga e define a hora de entrada
    public void ocuparVaga(LocalDateTime horaEntrada) {
        this.ocupado = true;
        this.horaEntrada = horaEntrada;
    }

    // Libera a vaga e redefine a hora de entrada para null
    public void liberarVaga() {
        this.ocupado = false;
        this.horaEntrada = null;
    }

    // Calcula o tempo de permanência em minutos
    public long calcularTempoPermanencia() {
        if (horaEntrada != null && ocupado) {
            Duration duracao = Duration.between(horaEntrada, LocalDateTime.now());
            return duracao.toMinutes();
        }
        return 0;
    }

    // Calcula o custo total com base no valorPorHora e tempo de permanência
    public double calcularCustoTotal() {
        long minutos = calcularTempoPermanencia();
        double horas = minutos / 60.0;
        return horas * valorPorHora;
    }

    // Verifica se a vaga é VIP
    public boolean verificarVip() {
        return this.isVip;
    }

    // Método toString atualizado para exibir os detalhes da vaga
    @Override
    public String toString() {
        return "Vaga{" +
                "idVaga=" + idVaga +
                ", identificador='" + identificador + '\'' +
                ", valorPorHora=" + valorPorHora +
                ", isVip=" + isVip +
                ", ocupado=" + ocupado +
                ", horaEntrada=" + (horaEntrada != null ? horaEntrada : "N/A") +
                ", idParqueRelacionado=" + idParque +
                '}';
    }

    // Método auxiliar para redefinir a vaga como VIP ou não
    public void definirVip(boolean vipStatus) {
        this.isVip = vipStatus;
    }

    // Método auxiliar para definir o valor por hora
    public void atualizarValorPorHora(double novoValor) {
        this.valorPorHora = novoValor;
    }

    public boolean ocupar(ClienteVeiculo clienteVeiculo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
