package Model.ModelDAO;

import model.DBConnect;  // Usando a classe DBConnect para conexão
import java.sql.*;

public class ClienteVagaDAO {

    // Inserir um novo registro de cliente e vaga
    public void inserirClienteVaga(int idCliente, String identificadorVaga, Timestamp horaEntrada, Timestamp horaSaida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO ClienteVaga (idCliente, identificadorVaga, horaEntrada, horaSaida, ativo) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);
            stmt.setTimestamp(3, horaEntrada);
            stmt.setTimestamp(4, horaSaida);
            stmt.setBoolean(5, true);  // Registro ativo por padrão
            stmt.executeUpdate();
            System.out.println("ClienteVaga inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir ClienteVaga: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Buscar cliente e vaga por ID e identificador da vaga
    public ResultSet buscarClienteVaga(int idCliente, String identificadorVaga) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM ClienteVaga WHERE idCliente = ? AND identificadorVaga = ? AND ativo = true";  // Apenas registros ativos
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ClienteVaga: " + e.getMessage());
        }
        return rs;
    }

    // Atualizar os dados de um cliente na vaga
    public void atualizarClienteVaga(int idCliente, String identificadorVaga, Timestamp horaEntrada, Timestamp horaSaida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE ClienteVaga SET horaEntrada = ?, horaSaida = ? WHERE idCliente = ? AND identificadorVaga = ? AND ativo = true";  // Atualiza apenas registros ativos
            stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, horaEntrada);
            stmt.setTimestamp(2, horaSaida);
            stmt.setInt(3, idCliente);
            stmt.setString(4, identificadorVaga);
            stmt.executeUpdate();
            System.out.println("ClienteVaga atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ClienteVaga: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Delete lógico de um cliente em uma vaga (desativa o registro)
    public void deletarClienteVaga(int idCliente, String identificadorVaga) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE ClienteVaga SET ativo = false WHERE idCliente = ? AND identificadorVaga = ?";  // Desativa o registro
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);
            stmt.executeUpdate();
            System.out.println("ClienteVaga desativado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar ClienteVaga: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Listar todos os clientes e vagas ativas
    public ResultSet listarClientesVagasAtivos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM ClienteVaga WHERE ativo = true";  // Apenas registros ativos
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar ClienteVaga ativos: " + e.getMessage());
        }
        return rs;
    }
}
