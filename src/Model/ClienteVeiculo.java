package Model;

import Model.Cliente;
import Model.Veiculo;
import java.util.ArrayList;
import java.util.List;

public class ClienteVeiculo {
    private  Cliente cliente;
    private Veiculo veiculo;

    // Construtor
    public ClienteVeiculo(Cliente cliente, Veiculo veiculo) {
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    // Métodos Getter e Setter para cliente
    public Cliente  getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Métodos Getter e Setter para veiculo
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    // Método para retornar informações completas do cliente e do veículo
    public String getInformacoes() {
        return "Cliente: " + cliente.getNome() + 
               ", Veículo: " + veiculo.getPlaca() + 
               " (" + veiculo.getModelo() + ", " + veiculo.getMarca() + ")";
    }

    // Método para verificar se o veículo pertence ao cliente
    public boolean pertenceAoCliente(Veiculo veiculo) {
        return this.veiculo.equals(veiculo);
    }

    // Método para exibir detalhes do cliente e veículo
    public void exibirDetalhes() {
        System.out.println("Detalhes do Cliente e Veículo:");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Documento: " + cliente.getDocumento());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Veículo: " + veiculo.getPlaca());
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Marca: " + veiculo.getMarca());
        System.out.println("Cor: " + veiculo.getCor());
    }

    // Método para verificar se o cliente está ativo
    public boolean isClienteAtivo() {
        return cliente.isStatus();
    }

    // Método toString para facilitar a exibição das informações
    @Override
    public String toString() {
        return "ClienteVeiculo{" +
               "cliente=" + cliente +
               ", veiculo=" + veiculo +
               '}';
    }
}
