package Model.ModelDAO;

import model.DBConnect;  // Usando a classe DBConnect para conexão
import java.sql.*;

public class VeiculoDAO {
    // Inserir um novo veículo
    
    public void inserirVeiculo(String placa, String modelo, String marca, int ano, String cor, String tipoVeiculo, int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO Veiculo (placa, modelo, marca, ano, cor, tipoVeiculo, idCliente, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            stmt.setString(2, modelo);
            stmt.setString(3, marca);
            stmt.setInt(4, ano);
            stmt.setString(5, cor);
            stmt.setString(6, tipoVeiculo);
            stmt.setInt(7, idCliente);
            stmt.setBoolean(8, true);  // Veículo ativo por padrão
            stmt.executeUpdate();
            System.out.println("Veículo inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir veículo: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão com o argumento conn
        }
    }

    // Buscar veículo por ID
    public ResultSet buscarVeiculo(int idVeiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM Veiculo WHERE idVeiculo = ? AND ativo = true";  // Apenas veículos ativos
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVeiculo);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo: " + e.getMessage());
        }
        return rs;
    }

    // Atualizar os dados de um veículo
    public void atualizarVeiculo(int idVeiculo, String placa, String modelo, String marca, int ano, String cor, String tipoVeiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE Veiculo SET placa = ?, modelo = ?, marca = ?, ano = ?, cor = ?, tipoVeiculo = ? WHERE idVeiculo = ? AND ativo = true";  // Atualiza apenas veículos ativos
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            stmt.setString(2, modelo);
            stmt.setString(3, marca);
            stmt.setInt(4, ano);
            stmt.setString(5, cor);
            stmt.setString(6, tipoVeiculo);
            stmt.setInt(7, idVeiculo);
            stmt.executeUpdate();
            System.out.println("Veículo atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão com o argumento conn
        }
    }

    // Delete lógico de um veículo (desativação)
    public void deletarVeiculo(int idVeiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE Veiculo SET ativo = false WHERE idVeiculo = ?";  // Desativa o veículo
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVeiculo);
            stmt.executeUpdate();
            System.out.println("Veículo desativado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar veículo: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão com o argumento conn
        }
    }

    // Listar todos os veículos ativos
    public ResultSet listarVeiculosAtivos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM Veiculo WHERE ativo = true";  // Apenas veículos ativos
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos ativos: " + e.getMessage());
        }
        return rs;
    }
}
