package Model.ModelDAO;

import model.DBConnect;  // Usando a classe DBConnect para conexão
import java.sql.*;

public class VagaDAO {

    // Inserir uma nova vaga
    public void inserirVaga(String identificador, double valorPorHora, boolean isVip, boolean ocupado, Integer idCliente, Timestamp horaEntrada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO Vaga (identificador, valorPorHora, isVip, ocupado, idCliente, horaEntrada, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, identificador);
            stmt.setDouble(2, valorPorHora);
            stmt.setBoolean(3, isVip);
            stmt.setBoolean(4, ocupado);
            if (idCliente != null) {
                stmt.setInt(5, idCliente);
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            if (horaEntrada != null) {
                stmt.setTimestamp(6, horaEntrada);
            } else {
                stmt.setNull(6, Types.TIMESTAMP);
            }
            stmt.setBoolean(7, true);  // Vaga ativa por padrão
            stmt.executeUpdate();
            System.out.println("Vaga inserida com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir vaga: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Buscar vaga por identificador
    public ResultSet buscarVaga(String identificador) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM Vaga WHERE identificador = ? AND ativo = true";  // Apenas vagas ativas
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, identificador);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar vaga: " + e.getMessage());
        }
        return rs;
    }

    // Atualizar os dados de uma vaga
    public void atualizarVaga(String identificador, boolean ocupado, Integer idCliente, Timestamp horaEntrada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE Vaga SET ocupado = ?, idCliente = ?, horaEntrada = ? WHERE identificador = ? AND ativo = true";  // Atualiza apenas vagas ativas
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, ocupado);
            if (idCliente != null) {
                stmt.setInt(2, idCliente);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            if (horaEntrada != null) {
                stmt.setTimestamp(3, horaEntrada);
            } else {
                stmt.setNull(3, Types.TIMESTAMP);
            }
            stmt.setString(4, identificador);
            stmt.executeUpdate();
            System.out.println("Vaga atualizada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar vaga: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Delete lógico de uma vaga (desativação)
    public void deletarVaga(String identificador) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE Vaga SET ativo = false WHERE identificador = ?";  // Desativa a vaga
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, identificador);
            stmt.executeUpdate();
            System.out.println("Vaga desativada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar vaga: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Listar todas as vagas ativas
    public ResultSet listarVagasAtivas() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM Vaga WHERE ativo = true";  // Apenas vagas ativas
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar vagas ativas: " + e.getMessage());
        }
        return rs;
    }
}
