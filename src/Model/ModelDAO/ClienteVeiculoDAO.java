package Model.ModelDAO;

import Model.Cliente;
import Model.ClienteVeiculo;
import Model.Veiculo;
import model.DBConnect; 
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteVeiculoDAO {

    // Método para inserir um novo ClienteVeiculo no banco de dados
    public void inserirClienteVeiculo(ClienteVeiculo clienteVeiculo) {
        String sqlCliente = "INSERT INTO Cliente (id, nome, documento, telefone, email, ativo) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlVeiculo = "INSERT INTO Veiculo (placa, modelo, marca, cor, tipo, idCliente) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente);
             PreparedStatement stmtVeiculo = conn.prepareStatement(sqlVeiculo)) {
             
            // Inserir Cliente
            stmtCliente.setInt(1, clienteVeiculo.getCliente().getIdCliente());
            stmtCliente.setString(2, clienteVeiculo.getCliente().getNome());
            stmtCliente.setString(3, clienteVeiculo.getCliente().getDocumento());
            stmtCliente.setString(4, clienteVeiculo.getCliente().getTelefone());
            stmtCliente.setString(5, clienteVeiculo.getCliente().getEmail());
            stmtCliente.setBoolean(6, clienteVeiculo.getCliente().isAtivo());
            stmtCliente.executeUpdate();

            // Inserir Veículo
            stmtVeiculo.setString(1, clienteVeiculo.getVeiculo().getPlaca());
            stmtVeiculo.setString(2, clienteVeiculo.getVeiculo().getModelo());
            stmtVeiculo.setString(3, clienteVeiculo.getVeiculo().getMarca());
            stmtVeiculo.setString(4, clienteVeiculo.getVeiculo().getCor());
            stmtVeiculo.setString(5, clienteVeiculo.getVeiculo().getTipoVeiculo());
            stmtVeiculo.setInt(6, clienteVeiculo.getCliente().getIdCliente()); // Assumindo que o idCliente é gerado na inserção do cliente
            stmtVeiculo.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao inserir ClienteVeiculo: " + e.getMessage());
        }
    }
    
    // Método para listar todos os ClienteVeiculo
    public List<ClienteVeiculo> listarClienteVeiculos() {
        List<ClienteVeiculo> clienteVeiculos = new ArrayList<>();
        String sql = "SELECT * FROM Cliente c INNER JOIN Veiculo v ON c.id = v.idCliente"; // Supondo que as tabelas estejam relacionadas por idCliente

        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                // Criação dos objetos Cliente e Veiculo
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getBoolean("ativo"),
                    rs.getString("nome"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );

                Veiculo veiculo = new Veiculo(
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getInt("ano"),
                    rs.getString("cor"),
                    rs.getString("tipo"),
                    cliente
                );

                // Definir o horário de entrada
                LocalDateTime horaEntrada = LocalDateTime.now();

                // Adiciona ClienteVeiculo à lista
                clienteVeiculos.add(new ClienteVeiculo(cliente, veiculo, horaEntrada));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar ClienteVeiculos: " + e.getMessage());
        }

        return clienteVeiculos;
    }

    // Método para buscar um ClienteVeiculo por ID do Cliente
    public ClienteVeiculo buscarClienteVeiculo(int idCliente) {
        ClienteVeiculo clienteVeiculo = null;
        String sql = "SELECT * FROM Cliente c INNER JOIN Veiculo v ON c.id = v.idCliente WHERE c.id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getBoolean("ativo"),
                    rs.getString("nome"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );

                Veiculo veiculo = new Veiculo(
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getInt("ano"),
                    rs.getString("cor"),
                    rs.getString("tipo"),
                    cliente
                );

                // Definir o horário de entrada
                LocalDateTime horaEntrada = LocalDateTime.now();

                clienteVeiculo = new ClienteVeiculo(cliente, veiculo, horaEntrada);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ClienteVeiculo: " + e.getMessage());
        }

        return clienteVeiculo;
    }

    // Método para atualizar um ClienteVeiculo
    public void atualizarClienteVeiculo(ClienteVeiculo clienteVeiculo) {
        String sqlCliente = "UPDATE Cliente SET nome = ?, documento = ?, telefone = ?, email = ?, ativo = ? WHERE id = ?";
        String sqlVeiculo = "UPDATE Veiculo SET modelo = ?, marca = ?, cor = ?, tipo = ? WHERE placa = ? AND idCliente = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente);
             PreparedStatement stmtVeiculo = conn.prepareStatement(sqlVeiculo)) {
             
            // Atualizar Cliente
            stmtCliente.setString(1, clienteVeiculo.getCliente().getNome());
            stmtCliente.setString(2, clienteVeiculo.getCliente().getDocumento());
            stmtCliente.setString(3, clienteVeiculo.getCliente().getTelefone());
            stmtCliente.setString(4, clienteVeiculo.getCliente().getEmail());
            stmtCliente.setBoolean(5, clienteVeiculo.getCliente().isAtivo());
            stmtCliente.setInt(6, clienteVeiculo.getCliente().getIdCliente());
            stmtCliente.executeUpdate();

            // Atualizar Veículo
            stmtVeiculo.setString(1, clienteVeiculo.getVeiculo().getModelo());
            stmtVeiculo.setString(2, clienteVeiculo.getVeiculo().getMarca());
            stmtVeiculo.setString(3, clienteVeiculo.getVeiculo().getCor());
            stmtVeiculo.setString(4, clienteVeiculo.getVeiculo().getTipoVeiculo());
            stmtVeiculo.setString(5, clienteVeiculo.getVeiculo().getPlaca());
            stmtVeiculo.setInt(6, clienteVeiculo.getCliente().getIdCliente());
            stmtVeiculo.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ClienteVeiculo: " + e.getMessage());
        }
    }

    // Método para deletar um ClienteVeiculo (lógico)
    public void deletarClienteVeiculo(int idCliente) {
        String sql = "UPDATE Cliente SET ativo = false WHERE id = ?"; // Desativa o cliente

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar ClienteVeiculo: " + e.getMessage());
        }
    }
}