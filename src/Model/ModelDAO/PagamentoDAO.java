package Model.ModelDAO;

import model.DBConnect;  // Usando a classe DBConnect para conexão
import java.sql.*;

public class PagamentoDAO {

    // Inserir um novo pagamento
    public void inserirPagamento(int idCliente, double valorTotal, String metodoPagamento, Timestamp dataHora, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO Pagamento (idCliente, valorTotal, metodoPagamento, dataHora, status, ativo) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setDouble(2, valorTotal);
            stmt.setString(3, metodoPagamento);
            stmt.setTimestamp(4, dataHora);
            stmt.setString(5, status);
            stmt.setBoolean(6, true);  // Registro ativo por padrão
            stmt.executeUpdate();
            System.out.println("Pagamento inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir pagamento: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Buscar pagamento por ID
    public ResultSet buscarPagamento(int idPagamento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM Pagamento WHERE idPagamento = ? AND ativo = true";  // Apenas registros ativos
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPagamento);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pagamento: " + e.getMessage());
        }
        return rs;
    }

    // Atualizar dados de um pagamento
    public void atualizarPagamento(int idPagamento, double valorTotal, String metodoPagamento, Timestamp dataHora, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE Pagamento SET valorTotal = ?, metodoPagamento = ?, dataHora = ?, status = ? WHERE idPagamento = ? AND ativo = true";  // Atualiza apenas registros ativos
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, valorTotal);
            stmt.setString(2, metodoPagamento);
            stmt.setTimestamp(3, dataHora);
            stmt.setString(4, status);
            stmt.setInt(5, idPagamento);
            stmt.executeUpdate();
            System.out.println("Pagamento atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pagamento: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Delete lógico de um pagamento (desativação)
    public void deletarPagamento(int idPagamento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "UPDATE Pagamento SET ativo = false WHERE idPagamento = ?";  // Desativa o registro
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPagamento);
            stmt.executeUpdate();
            System.out.println("Pagamento desativado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar pagamento: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Listar todos os pagamentos ativos
    public ResultSet listarPagamentosAtivos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM Pagamento WHERE ativo = true";  // Apenas pagamentos ativos
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar pagamentos ativos: " + e.getMessage());
        }
        return rs;
    }
}
