package Model.ModelDAO;

import model.DBConnect;  // Usando a classe DBConnect para conexão
import java.sql.*;

public class ParqueDeEstacionamentoDAO {

    // Inserir um novo parque de estacionamento
    public void inserirParqueDeEstacionamento(String nome, String endereco, int capacidadeTotal, int vagasDisponiveis) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO ParqueDeEstacionamento (nome, endereco, capacidadeTotal, vagasDisponiveis, ativo) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setInt(3, capacidadeTotal);
            stmt.setInt(4, vagasDisponiveis);
            stmt.setBoolean(5, true);  // Registro ativo por padrão
            stmt.executeUpdate();
            System.out.println("Parque de Estacionamento inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir parque de estacionamento: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Buscar parque de estacionamento por ID
    public ResultSet buscarParqueDeEstacionamento(int idParque) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM ParqueDeEstacionamento WHERE idParque = ? AND ativo = true";  // Apenas registros ativos
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idParque);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar parque de estacionamento: " + e.getMessage());
        }
        return rs;
    }

    // Atualizar dados do parque de estacionamento
    public void atualizarParqueDeEstacionamento(int idParque, String nome, String endereco, int capacidadeTotal, int vagasDisponiveis) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE ParqueDeEstacionamento SET nome = ?, endereco = ?, capacidadeTotal = ?, vagasDisponiveis = ? WHERE idParque = ? AND ativo = true";  // Atualiza apenas registros ativos
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setInt(3, capacidadeTotal);
            stmt.setInt(4, vagasDisponiveis);
            stmt.setInt(5, idParque);
            stmt.executeUpdate();
            System.out.println("Parque de Estacionamento atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar parque de estacionamento: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Delete lógico de um parque de estacionamento (desativação)
    public void deletarParqueDeEstacionamento(int idParque) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE ParqueDeEstacionamento SET ativo = false WHERE idParque = ?";  // Desativa o registro
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idParque);
            stmt.executeUpdate();
            System.out.println("Parque de Estacionamento desativado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar parque de estacionamento: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Listar todos os parques de estacionamento ativos
    public ResultSet listarParquesAtivos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM ParqueDeEstacionamento WHERE ativo = true";  // Apenas parques ativos
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar parques de estacionamento ativos: " + e.getMessage());
        }
        return rs;
    }
}
