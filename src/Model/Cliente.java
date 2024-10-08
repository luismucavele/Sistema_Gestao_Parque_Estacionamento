package Model;

import java.util.ArrayList;

public abstract class Cliente extends Pessoa {
    protected static int idCounter = 1; // Contador de IDs
    protected int idCliente; // ID único do cliente
    protected ArrayList<Veiculo> veiculos; // Lista de veículos do cliente

    public Cliente(String nome, String documento, String telefone, String email) {
        super(nome, documento, telefone, email); // Chama o construtor da classe base
        this.idCliente = idCounter++; // Atribui ID único ao cliente
        this.veiculos = new ArrayList<>(); // Inicializa a lista de veículos
    }

    public abstract double calcularDesconto(double valor); // Método abstrato para cálculo de desconto

    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo); // Adiciona um veículo ao cliente
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos; // Retorna a lista de veículos
    }

    // Getters e Setters
    public int getIdCliente() {
        return idCliente; // Retorna o ID do cliente
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente; // Define o ID do cliente
    }
    
    public String getNome() {
        return nome; // Retorna o nome do cliente
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do cliente
    }

    public String getDocumento() {
        return documento; // Retorna o documento do cliente
    }

    public void setDocumento(String documento) {
        this.documento = documento; // Define o documento do cliente
    }

    public String getTelefone() {
        return telefone; // Retorna o telefone do cliente
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone; // Define o telefone do cliente
    }

    public String getEmail() {
        return email; // Retorna o email do cliente
    }

    public void setEmail(String email) {
        this.email = email; // Define o email do cliente
    }
}
