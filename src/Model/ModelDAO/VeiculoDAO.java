package Model.ModelDAO;

import Model.Veiculo;
import model.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    // Método para inserir um veículo no banco de dados
    public void inserirVeiculo(Veiculo veiculo, int idCliente) throws SQLException {
        String sql = "INSERT INTO veiculos (placa, modelo, marca, cor, tipoVeiculo, cliente_idCliente) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnect.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getMarca());
            stmt.setString(4, veiculo.getCor());
            stmt.setString(5, veiculo.getTipoVeiculo());
            stmt.setInt(6, idCliente);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um veículo pelo número da placa
    public Veiculo buscarVeiculo(String placa) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        
        try (Connection conn = DBConnect.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Veiculo(
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getString("cor"),
                    rs.getString("tipoVeiculo") // Incluindo o tipo de veículo
                );
            }
        }
        return null;
    }

    // Método para listar todos os veículos no banco de dados
    public List<Veiculo> listarVeiculos() throws SQLException {
        String sql = "SELECT * FROM veiculos";
        List<Veiculo> veiculos = new ArrayList<>();
        
        try (Connection conn = DBConnect.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                veiculos.add(new Veiculo(
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getString("cor"),
                    rs.getString("tipoVeiculo") // Incluindo o tipo de veículo
                ));
            }
        }
        return veiculos;
    }

    // Método para atualizar os dados de um veículo
    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculos SET modelo = ?, marca = ?, cor = ?, tipoVeiculo = ? WHERE placa = ?";
        
        try (Connection conn = DBConnect.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getCor());
            stmt.setString(4, veiculo.getTipoVeiculo());
            stmt.setString(5, veiculo.getPlaca());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um veículo do banco de dados
    public void excluirVeiculo(String placa) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE placa = ?";
        
        try (Connection conn = DBConnect.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, placa);
            stmt.executeUpdate();
        }
    }
}
