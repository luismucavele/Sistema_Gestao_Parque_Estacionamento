package Model;

import java.time.LocalDateTime;

public class Pagamento {
    private int idPagamento;
    private Cliente cliente;
    private double valorTotal;
    private String metodoPagamento;
    private LocalDateTime dataHora;
    private String status;

    public Pagamento(int idPagamento, Cliente cliente, double valorTotal, String metodoPagamento, LocalDateTime dataHora, String status) {
        this.idPagamento = idPagamento;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.metodoPagamento = metodoPagamento;
        this.dataHora = dataHora;
        this.status = status;
    }

    // Getters e Setters
    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Métodos adicionais
    public void cancelarPagamento() {
        this.status = "Cancelado";
    }

    public void concluirPagamento() {
        this.status = "Concluído";
    }

    public boolean isPagamentoPendente() {
        return this.status.equals("Pendente");
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", valorTotal=" + valorTotal +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", dataHora=" + dataHora +
                ", status='" + status + '\'' +
                '}';
    }
}
