package Model.ModelDAO;

import Model.ClienteVaga;
import model.DBConnect;  // Usando a classe DBConnect para conexão
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteVagaDAO {
    
    // Buscar clientes estacionados
    public List<ClienteVaga> buscarClientesEstacionados() {
        List<ClienteVaga> clientesEstacionados = new ArrayList<>();
        String sql = "SELECT c.idCliente, c.nome, c.residencia, c.contacto, v.matricula, v.cor, v.tipoPagamento, v.valorPorHora " +
                     "FROM Cliente c " +
                     "JOIN ClienteVaga cv ON c.idCliente = cv.idCliente " +
                     "JOIN Veiculo v ON v.idCliente = c.idCliente " +
                     "WHERE cv.horaSaida IS NULL";  // Apenas clientes que ainda estão estacionados (sem hora de saída)

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                String residencia = rs.getString("residencia");
                String contacto = rs.getString("contacto");
                String matricula = rs.getString("matricula");
                String cor = rs.getString("cor");
                String tipoPagamento = rs.getString("tipoPagamento");
                double valorPorHora = rs.getDouble("valorPorHora");

                ClienteVaga clienteVaga = new ClienteVaga(idCliente, nome, residencia, contacto, matricula, cor, tipoPagamento, valorPorHora);
                clientesEstacionados.add(clienteVaga);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar clientes estacionados: " + e.getMessage());
        }

        return clientesEstacionados;  // Retorna a lista de clientes estacionados
    }

    // Inserir um novo registro de cliente e vaga
    public void inserirClienteVaga(int idCliente, String identificadorVaga, Timestamp horaEntrada) {
        String sql = "INSERT INTO ClienteVaga (idCliente, identificadorVaga, horaEntrada, ativo) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);
            stmt.setTimestamp(3, horaEntrada);
            stmt.setBoolean(4, true);  // Registro ativo por padrão
            stmt.executeUpdate();
            System.out.println("ClienteVaga inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir ClienteVaga: " + e.getMessage());
        }
    }

    // Buscar cliente e vaga por ID e identificador da vaga
    public ResultSet buscarClienteVaga(int idCliente, String identificadorVaga) {
        String sql = "SELECT * FROM ClienteVaga WHERE idCliente = ? AND identificadorVaga = ? AND ativo = true";  // Apenas registros ativos

        try {
            Connection conn = DBConnect.getConnection();  // Obtém a conexão
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ClienteVaga: " + e.getMessage());
            return null;
        }
    }
    // Atualizar os dados de um cliente na vaga
    public void atualizarClienteVaga(int idCliente, String identificadorVaga, Timestamp horaEntrada, Timestamp horaSaida) {
        String sql = "UPDATE ClienteVaga SET horaEntrada = ?, horaSaida = ? WHERE idCliente = ? AND identificadorVaga = ? AND ativo = true";  // Atualiza apenas registros ativos
        
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, horaEntrada);
            stmt.setTimestamp(2, horaSaida);
            stmt.setInt(3, idCliente);
            stmt.setString(4, identificadorVaga);
            stmt.executeUpdate();
            System.out.println("ClienteVaga atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ClienteVaga: " + e.getMessage());
        }
    }

    // Delete lógico de um cliente em uma vaga (desativa o registro)
    public void deletarClienteVaga(int idCliente, String identificadorVaga) {
        String sql = "UPDATE ClienteVaga SET ativo = false WHERE idCliente = ? AND identificadorVaga = ?";  // Desativa o registro
        
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);
            stmt.executeUpdate();
            System.out.println("ClienteVaga desativado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar ClienteVaga: " + e.getMessage());
        }
    }

    // Listar todos os clientes e vagas ativas
    public ResultSet listarClientesVagasAtivos() {
        String sql = "SELECT * FROM ClienteVaga WHERE ativo = true";  // Apenas registros ativos
        
        try {
            Connection conn = DBConnect.getConnection();  // Obtém a conexão
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar ClienteVaga ativos: " + e.getMessage());
            return null;
        }
    }
}
