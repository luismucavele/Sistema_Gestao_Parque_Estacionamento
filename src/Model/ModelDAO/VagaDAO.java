package Model.ModelDAO;

import Model.EspacoEstacionamento;
import Model.Vaga;
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
    public static Vaga getVagaPorIdentificador(String identificador) {
        Vaga vaga = null;
        String sql = "SELECT * FROM vagas WHERE id = ?";  // Altere conforme o nome da sua tabela

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vaga = new Vaga(
                    rs.getString("id"),
                    rs.getDouble("valor_por_hora"),
                    rs.getBoolean("is_vip"),
                    rs.getBoolean("ocupado"),
                    null, // Iniciar como null e definir depois
                    null // Hora de entrada inicialmente nula
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter vaga: " + e.getMessage());
        }

        return vaga;
    }

    // Método para atualizar a vaga no banco de dados
    public static void atualizarVaga(Vaga vaga) {
        String sql = "UPDATE vagas SET ocupado = ?, cliente_id = ?, hora_entrada = ? WHERE id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, vaga.isOcupado());
            stmt.setInt(2, vaga.getCliente() != null ? vaga.getCliente().getIdCliente() : null);
            stmt.setTimestamp(3, vaga.getHoraEntrada() != null ? Timestamp.valueOf(vaga.getHoraEntrada()) : null);
            stmt.setString(4, vaga.getIdentificador());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar vaga: " + e.getMessage());
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
