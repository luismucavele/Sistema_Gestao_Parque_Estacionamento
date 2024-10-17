package Model.ModelDAO;

import model.DBConnect;  // Usando a classe DBConnect para conexão
import java.sql.*;

public class ClienteMensalistaDAO {

    // Inserir um novo cliente mensalista
    public void inserirClienteMensalista(int idCliente, Date prazoSaida, double taxaMensal, Date dataRegistro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO ClienteMensalista (idCliente, prazoSaida, taxaMensal, dataRegistro, ativo) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setDate(2, prazoSaida);
            stmt.setDouble(3, taxaMensal);
            stmt.setDate(4, dataRegistro);
            stmt.setBoolean(5, true);  // Cliente ativo ao ser inserido
            stmt.executeUpdate();
            System.out.println("Cliente mensalista inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente mensalista: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Buscar cliente mensalista por ID
    public ResultSet buscarClienteMensalista(int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM ClienteMensalista WHERE idCliente = ? AND ativo = true";  // Apenas busca clientes ativos
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente mensalista: " + e.getMessage());
        }
        return rs;
    }

    // Atualizar os dados de um cliente mensalista
    public void atualizarClienteMensalista(int idCliente, Date prazoSaida, double taxaMensal, Date dataRegistro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE ClienteMensalista SET prazoSaida = ?, taxaMensal = ?, dataRegistro = ? WHERE idCliente = ? AND ativo = true";  // Apenas atualiza se estiver ativo
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, prazoSaida);
            stmt.setDouble(2, taxaMensal);
            stmt.setDate(3, dataRegistro);
            stmt.setInt(4, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente mensalista atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente mensalista: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Delete lógico de um cliente mensalista (desativa o cliente)
    public void deletarClienteMensalista(int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE ClienteMensalista SET ativo = false WHERE idCliente = ?";  // Desativa o cliente em vez de deletar
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente mensalista desativado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar cliente mensalista: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Listar todos os clientes mensalistas ativos
    public ResultSet listarClientesMensalistasAtivos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM ClienteMensalista WHERE ativo = true";  // Lista apenas clientes ativos
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes mensalistas ativos: " + e.getMessage());
        }
        return rs;
    }
}
