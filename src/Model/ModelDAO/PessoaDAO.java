package Model.ModelDAO;

import model.DBConnect;  // Usando a classe DBConnect para conexão
import java.sql.*;

public class PessoaDAO {

    // Inserir uma nova pessoa
    public void inserirPessoa(String nome, String documento, String telefone, String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO Pessoa (nome, documento, telefone, email, ativo) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, documento);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setBoolean(5, true);  // Registro ativo por padrão
            stmt.executeUpdate();
            System.out.println("Pessoa inserida com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir pessoa: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Buscar pessoa por ID
    public ResultSet buscarPessoa(int idPessoa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM Pessoa WHERE idPessoa = ? AND ativo = true";  // Apenas registros ativos
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pessoa: " + e.getMessage());
        }
        return rs;
    }

    // Atualizar dados de uma pessoa
    public void atualizarPessoa(int idPessoa, String nome, String documento, String telefone, String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE Pessoa SET nome = ?, documento = ?, telefone = ?, email = ? WHERE idPessoa = ? AND ativo = true";  // Atualiza apenas registros ativos
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, documento);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setInt(5, idPessoa);
            stmt.executeUpdate();
            System.out.println("Dados da pessoa atualizados com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pessoa: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Delete lógico de uma pessoa (desativação)
    public void deletarPessoa(int idPessoa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE Pessoa SET ativo = false WHERE idPessoa = ?";  // Desativa o registro
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.executeUpdate();
            System.out.println("Pessoa desativada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar pessoa: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Listar todas as pessoas ativas
    public ResultSet listarPessoasAtivas() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM Pessoa WHERE ativo = true";  // Apenas pessoas ativas
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar pessoas ativas: " + e.getMessage());
        }
        return rs;
    }
}
