package Model;

import Model.Cliente;
import Model.Veiculo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteVeiculo {
    private  Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime horaEntradaEntrada;
    private boolean estacionado;

    public ClienteVeiculo(Cliente cliente, Veiculo veiculo, LocalDateTime horaEntradaEntrada) {
        if (cliente == null || veiculo == null) {
            throw new IllegalArgumentException("Cliente e veículo não podem ser nulos");
        }
        if (horaEntradaEntrada.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Hora de entrada não pode ser no futuro");
        }
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.horaEntradaEntrada = horaEntradaEntrada;
        this.estacionado = estacionado; // Assuming that the vehicle is parked when creating the object
    }

    
        public LocalDateTime getHoraEntradaEntrada() {
        return horaEntradaEntrada;
    }

    public void setHoraEntradaEntrada(LocalDateTime horaEntradaEntrada) {
        this.horaEntradaEntrada = horaEntradaEntrada;
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
    public boolean isEstacionado() {
        return estacionado;
    }

    public void setEstacionado(boolean estacionado) {
        this.estacionado = estacionado;
    }

    // Método para retornar informações completas do cliente e do veículo
    public String getInformacoes() {
        return "Cliente: " + cliente.getNome() + 
               ", Veículo: " + veiculo.getPlaca() + 
               " (" + veiculo.getModelo() + ", " + veiculo.getMarca() + ")";
    }
     public boolean isClienteAtivo() {
        return cliente.isStatus();
    }

    public long calcularDuracaoEstacionamento() {
        // Assumindo que o veículo está estacionado
        LocalDateTime agora = LocalDateTime.now();
        Duration duracao = Duration.between(horaEntradaEntrada, agora);
        return duracao.toMinutes(); // Retorna a duração em minutos
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

    // Método toString para facilitar a exibição das informações
    @Override
    public String toString() {
        return "ClienteVeiculo{" +
               "cliente=" + cliente +
               ", veiculo=" + veiculo +
               '}';
    }
}
