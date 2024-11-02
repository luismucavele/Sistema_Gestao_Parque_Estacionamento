package Model.ModelDAO;

import Model.Vaga;
import model.DBConnect;

import java.sql.*;
import java.time.LocalDateTime;

public class VagaDAO {

    // Inserir uma nova vaga no banco de dados
    public void inserirVaga(String identificador, double valorPorHora, boolean isVip, int idParqueRelacionado) throws Exception {
        String sql = "INSERT INTO vaga (identificador, valor_por_hora, is_vip, ocupado, id_parque_relacionado, ativo) VALUES (?, ?, ?, ?, ?, true)";
        
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            stmt.setDouble(2, valorPorHora);
            stmt.setBoolean(3, isVip);
            stmt.setBoolean(4, false);  // Ocupado começa como falso ao criar nova vaga
            stmt.setInt(5, idParqueRelacionado);
            stmt.executeUpdate();
            System.out.println("Vaga inserida com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir vaga: " + e.getMessage());
        }
    }

    // Atualizar informações da vaga
    public void atualizarVaga(int idVaga, double valorPorHora, boolean isVip, boolean ocupado, Integer idCliente, LocalDateTime horaEntrada) {
        String sql = "UPDATE vaga SET valor_por_hora = ?, is_vip = ?, ocupado = ?, id_cliente = ?, hora_entrada = ? WHERE id_vaga = ? AND ativo = true";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valorPorHora);
            stmt.setBoolean(2, isVip);
            stmt.setBoolean(3, ocupado);
            
            if (idCliente != null) {
                stmt.setInt(4, idCliente);
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            
            if (horaEntrada != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(horaEntrada));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }
            stmt.setInt(6, idVaga);
            stmt.executeUpdate();
            System.out.println("Vaga atualizada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar vaga: " + e.getMessage());
        }
    }

    // Buscar vaga por ID
    public Vaga buscarVagaPorId(int idVaga) {
        Vaga vaga = null;
        String sql = "SELECT * FROM vaga WHERE id_vaga = ? AND ativo = true";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vaga = new Vaga(
                    rs.getInt("id_vaga"),
                    rs.getString("identificador"),
                    rs.getDouble("valor_por_hora"),
                    rs.getBoolean("is_vip"),
                    rs.getBoolean("ocupado"),
                    rs.getTimestamp("hora_entrada") != null ? rs.getTimestamp("hora_entrada").toLocalDateTime() : null,
                    rs.getInt("id_parque_relacionado")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar vaga por ID: " + e.getMessage());
        }
        return vaga;
    }

    // Buscar vaga por identificador
    public Vaga buscarVagaPorIdentificador(String identificador) {
        Vaga vaga = null;
        String sql = "SELECT * FROM vaga WHERE identificador = ? AND ativo = true";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vaga = new Vaga(
                    rs.getInt("id_vaga"),
                    rs.getString("identificador"),
                    rs.getDouble("valor_por_hora"),
                    rs.getBoolean("is_vip"),
                    rs.getBoolean("ocupado"),
                    rs.getTimestamp("hora_entrada") != null ? rs.getTimestamp("hora_entrada").toLocalDateTime() : null,
                    rs.getInt("id_parque_relacionado")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar vaga por identificador: " + e.getMessage());
        }
        return vaga;
    }

    // Listar todas as vagas ativas
    public ResultSet listarVagasAtivas() {
        String sql = "SELECT * FROM vaga WHERE ativo = true";
        ResultSet rs = null;

        try {
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar vagas ativas: " + e.getMessage());
        }
        return rs;
    }

    // Excluir (lógica) uma vaga - desativar a vaga
public void deletarVaga(int idVaga) {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
        conn = DBConnect.getConnection();  // Obtém a conexão
        String sql = "UPDATE vaga SET ativo = false WHERE id_vaga = ?";  // Desativa a vaga
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idVaga);
        stmt.executeUpdate();
        System.out.println("Vaga desativada com sucesso.");
    } catch (SQLException e) {
        System.err.println("Erro ao desativar vaga: " + e.getMessage());
    } finally {
        DBConnect.closeConnection(conn);  // Fecha a conexão
    }
}


    // Verificar disponibilidade de vaga
    public boolean verificarDisponibilidade(int idVaga) {
        boolean disponivel = false;
        String sql = "SELECT ocupado FROM vaga WHERE id_vaga = ? AND ativo = true";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                disponivel = !rs.getBoolean("ocupado");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar disponibilidade da vaga: " + e.getMessage());
        }
        return disponivel;
    }
}
