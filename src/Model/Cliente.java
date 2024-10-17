package Model;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    private int idCliente;
    private boolean status;
    private ArrayList<Veiculo> veiculos;
    private ArrayList<Pagamento> pagamentos;

    // Construtor
    public Cliente(int idCliente, boolean status, String nome, String documento, String telefone, String email) {
        super(nome, documento, telefone, email); // Chamando o construtor da classe Pessoa
        this.idCliente = idCliente;
        this.status = status; // Ou você pode inicializar como 'true' por padrão, se quiser
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public ArrayList<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(ArrayList<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    // Métodos adicionais para manipular veículos
    public void adicionarVeiculo(Veiculo veiculo) {
        if (!veiculos.contains(veiculo)) {
            this.veiculos.add(veiculo);
            System.out.println("Veículo adicionado: " + veiculo);
        } else {
            System.out.println("O veículo já está associado ao cliente.");
        }
    }

    public void removerVeiculo(Veiculo veiculo) {
        if (this.veiculos.contains(veiculo)) {
            this.veiculos.remove(veiculo);
            System.out.println("Veículo removido: " + veiculo);
        } else {
            System.out.println("O veículo não está associado a este cliente.");
        }
    }

    // Métodos adicionais para manipular pagamentos
    public void adicionarPagamento(Pagamento pagamento) {
        if (!pagamentos.contains(pagamento)) {
            this.pagamentos.add(pagamento);
            System.out.println("Pagamento adicionado: " + pagamento);
        } else {
            System.out.println("Este pagamento já está associado ao cliente.");
        }
    }

    public void removerPagamento(Pagamento pagamento) {
        if (this.pagamentos.contains(pagamento)) {
            this.pagamentos.remove(pagamento);
            System.out.println("Pagamento removido: " + pagamento);
        } else {
            System.out.println("Este pagamento não está associado ao cliente.");
        }
    }

    // Método toString ajustado
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", status=" + (status ? "Ativo" : "Inativo") +
                ", totalVeiculos=" + veiculos.size() +
                ", totalPagamentos=" + pagamentos.size() +
                '}';
    }
}
