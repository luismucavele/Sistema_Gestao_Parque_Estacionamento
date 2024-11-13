package Model.ModelDAO;

import Model.Vaga;
import model.DBConnect;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VagaDAO {

    // Inserir uma nova vaga no banco de dados
    public void inserirVaga(String identificador, double valorPorHora, boolean isVip, int idParqueRelacionado) throws Exception {
        String sql = "INSERT INTO vaga (identificador, valorPorHora, isVip, ocupado, idParque, ativo) VALUES (?, ?, ?, ?, ?, true)";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        String sql = "UPDATE vaga SET valorPorHora = ?, isVip = ?, ocupado = ?, idCliente = ?, horaEntrada = ? WHERE idVaga = ? AND ativo = true";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vaga = new Vaga(
                        rs.getInt("idVaga"),
                        rs.getString("identificador"),
                        rs.getDouble("valorPorHora"),
                        rs.getBoolean("isVip"),
                        rs.getBoolean("ocupado"),
                        rs.getTimestamp("horaEntrada") != null ? rs.getTimestamp("horaEntrada").toLocalDateTime() : null,
                        rs.getInt("idParque")
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

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vaga = new Vaga(
                        rs.getInt("idVaga"),
                        rs.getString("identificador"),
                        rs.getDouble("valorPorHora"),
                        rs.getBoolean("isVip"),
                        rs.getBoolean("ocupado"),
                        rs.getTimestamp("horaEntrada") != null ? rs.getTimestamp("hora_entrada").toLocalDateTime() : null,
                        rs.getInt("idParque")
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

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    // Reativar vaga
    public void reativarVaga(int idVaga) {
        String sql = "UPDATE vaga SET ativo = true WHERE id_vaga = ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVaga);
            stmt.executeUpdate();
            System.out.println("Vaga reativada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao reativar vaga: " + e.getMessage());
        }
    }

    // Contar espaços disponíveis
    public int contarEspacosDisponiveis() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM vaga WHERE ativo = true AND ocupado = false";

        try (Connection conn = DBConnect.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar espaços disponíveis: " + e.getMessage());
        }
        return count;
    }

    // Listar espaços disponíveis
    public ResultSet listarEspacosDisponiveis() {
        String sql = "SELECT * FROM vaga WHERE ativo = true AND ocupado = false";
        ResultSet rs = null;

        try {
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao listar espaços disponíveis: " + e.getMessage());
        }
        return rs;
    }

    // Ler (read) vaga por ID
    public Vaga read(String idVaga) {
        Vaga vaga = null;
        String sql = "SELECT * FROM vaga WHERE idvaga = ? AND ativo = true";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(idVaga));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vaga = new Vaga(
                        rs.getInt("idvaga"),
                        rs.getString("identificador"),
                        rs.getDouble("valorPorHora"),
                        rs.getBoolean("isvip"),
                        rs.getBoolean("ocupado"),
                        rs.getTimestamp("horaentrada") != null ? rs.getTimestamp("hora_entrada").toLocalDateTime() : null,
                        rs.getInt("idparquerelacionado")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler vaga: " + e.getMessage());
        }
        return vaga;
    }

// Método na classe VagaDAO para listar apenas os identificadores das vagas ativas
    public List<String> listarIdentificadoresVagasAtivas() {
        List<String> identificadores = new ArrayList<>();
        String sql = "SELECT identificador FROM vaga WHERE ativo = true";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                identificadores.add(rs.getString("identificador"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar identificadores das vagas: " + e.getMessage());
        }

        return identificadores;
    }
// Listar espaços disponíveis

    public List<Vaga> listarEspacosDisponivel() {
        List<Vaga> espacosDisponiveis = new ArrayList<>();
        String sql = "SELECT * FROM vaga WHERE ativo = true AND ocupado = false";

        try (Connection conn = DBConnect.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vaga vaga = new Vaga(
                        rs.getInt("idvaga"),
                        rs.getString("identificador"),
                        rs.getDouble("valorPorHora"),
                        rs.getBoolean("isVip"),
                        rs.getBoolean("ocupado"),
                        rs.getTimestamp("horaEntrada") != null ? rs.getTimestamp("horaEntrada").toLocalDateTime() : null,
                        rs.getInt("idParque")
                );
                espacosDisponiveis.add(vaga);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar espaços disponíveis: " + e.getMessage());
        }
        return espacosDisponiveis;
    }
}
