
package Model.ModelDAO;

import Model.*;
import java.sql.*;

public class EspacoEstacionamentoDAO {
    public void create(EspacoEstacionamento espaco) throws SQLException {
        String sql = "INSERT INTO EspacoEstacionamento (identificador, valorPorHora, isVip, ocupado, cliente_idCliente) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, espaco.getIdentificador());
            stmt.setDouble(2, espaco.getValorPorHora());
            stmt.setBoolean(3, espaco.isVip());
            stmt.setBoolean(4, espaco.isOcupado());
            stmt.setInt(5, espaco.getCliente() != null ? espaco.getCliente().getIdCliente() : 0);
            stmt.executeUpdate();
        }
    }

    public EspacoEstacionamento read(String identificador) throws SQLException {
        String sql = "SELECT * FROM EspacoEstacionamento WHERE identificador = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EspacoEstacionamento(
                    rs.getString("identificador"),
                    rs.getDouble("valorPorHora"),
                    rs.getBoolean("isVip")
                );
            }
        }
        return null;
    }

    public void update(EspacoEstacionamento espaco) throws SQLException {
        String sql = "UPDATE EspacoEstacionamento SET valorPorHora = ?, isVip = ?, ocupado = ?, cliente_idCliente = ? WHERE identificador = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, espaco.getValorPorHora());
            stmt.setBoolean(2, espaco.isVip());
            stmt.setBoolean(3, espaco.isOcupado());
            stmt.setInt(4, espaco.getCliente() != null ? espaco.getCliente().getIdCliente() : 0);
            stmt.setString(5, espaco.getIdentificador());
            stmt.executeUpdate();
        }
    }

    public void delete(String identificador) throws SQLException {
        String sql = "DELETE FROM EspacoEstacionamento WHERE identificador = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            stmt.executeUpdate();
        }
    }
}

