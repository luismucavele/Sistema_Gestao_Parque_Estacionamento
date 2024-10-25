package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

    private int idCliente;
    private boolean status;
    private List<Veiculo> veiculos;
    private List<Pagamento> pagamentos;

    // Construtor
    public Cliente(int idCliente, boolean status, String nome, String documento, String telefone, String email) {
        super(nome, documento, telefone, email); // Chamando o construtor da classe Pessoa
        this.idCliente = idCliente;
        this.status = status;
        this.veiculos = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
        }

    // Getters e Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    // Métodos para manipular veículos
    public void adicionarVeiculo(Veiculo veiculo) {
        if (!veiculos.contains(veiculo)) {
            veiculos.add(veiculo);
            System.out.println("Veículo adicionado: " + veiculo);
        } else {
            System.out.println("O veículo já está associado ao cliente.");
        }
    }

    public void removerVeiculo(Veiculo veiculo) {
        if (veiculos.contains(veiculo)) {
            veiculos.remove(veiculo);
            System.out.println("Veículo removido: " + veiculo);
        } else {
            System.out.println("O veículo não está associado a este cliente.");
        }
    }

    // Métodos para manipular pagamentos
    public void adicionarPagamento(Pagamento pagamento) {
        if (!pagamentos.contains(pagamento)) {
            pagamentos.add(pagamento);
            System.out.println("Pagamento adicionado: " + pagamento);
        } else {
            System.out.println("Este pagamento já está associado ao cliente.");
        }
    }

    public void removerPagamento(Pagamento pagamento) {
        if (pagamentos.contains(pagamento)) {
            pagamentos.remove(pagamento);
            System.out.println("Pagamento removido: " + pagamento);
        } else {
            System.out.println("Este pagamento não está associado ao cliente.");
        }
    }

    // Exclusão lógica (status inativo)
    public void desativarCliente() {
        this.status = false;
        System.out.println("Cliente desativado: " + getNome());
    }
        public boolean isAtivo() {
                return status;
        }
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + getNome() + '\'' +
                ", documento='" + getDocumento() + '\'' +
                ", status=" + (status ? "Ativo" : "Inativo") +
                ", totalVeiculos=" + veiculos.size() +
                ", totalPagamentos=" + pagamentos.size() +
                '}';
    }
}
