package Model.ModelDAO;
import Model.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void create(Cliente clienteVeiculo) throws SQLException {
        String sql = "INSERT INTO ClienteVeiculo (nome, residencia, contacto, matricula,"
                + " corCarro, valorPorHora, tipoPagamento, estacionado, dataHoraEntrada, espacoEstacionado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clienteVeiculo.getNome());
            stmt.setString(2, clienteVeiculo.getResidencia());
            stmt.setString(3, clienteVeiculo.getContacto());
            stmt.setString(4, clienteVeiculo.getMatricula());
            stmt.setString(5, clienteVeiculo.getCorCarro());
            stmt.setDouble(6, clienteVeiculo.getValorPorHora());
            stmt.setString(7, clienteVeiculo.getTipoPagamento());
            stmt.setBoolean(8, clienteVeiculo.isEstacionado());
            stmt.setObject(9, clienteVeiculo.getDataHoraEntrada());
            stmt.setInt(10, clienteVeiculo.getEspacoEstacionado());
            stmt.executeUpdate();
        }
    }

    public Cliente read(int idCliente) throws SQLException {
        String sql = "SELECT * FROM ClienteVeiculo WHERE idCliente = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getString("nome"),
                    rs.getString("residencia"),
                    rs.getString("contacto"),
                    rs.getString("matricula"),
                    rs.getString("corCarro"),
                    rs.getDouble("valorPorHora"),
                    rs.getString("tipoPagamento"),
                    rs.getBoolean("estacionado")
                );
            }
        }
        return null;
    }
/**
  metodo de atualizar os clientes consoante a banco de dados
 * @param clienteVeiculo
 * @throws SQLException 
 */
    public void update(Cliente clienteVeiculo) throws SQLException {
        String sql = "UPDATE ClienteVeiculo SET nome = ?, residencia = ?, contacto = ?, matricula = ?, corCarro = ?, valorPorHora = ?, tipoPagamento = ?, estacionado = ?, dataHoraEntrada = ?, espacoEstacionado = ? WHERE idCliente = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clienteVeiculo.getNome());
            stmt.setString(2, clienteVeiculo.getResidencia());
            stmt.setString(3, clienteVeiculo.getContacto());
            stmt.setString(4, clienteVeiculo.getMatricula());
            stmt.setString(5, clienteVeiculo.getCorCarro());
            stmt.setDouble(6, clienteVeiculo.getValorPorHora());
            stmt.setString(7, clienteVeiculo.getTipoPagamento());
            stmt.setBoolean(8, clienteVeiculo.isEstacionado());
            stmt.setObject(9, clienteVeiculo.getDataHoraEntrada());
            stmt.setInt(10, clienteVeiculo.getEspacoEstacionado());
            stmt.setInt(11, clienteVeiculo.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public void delete(int idCliente) throws SQLException {
        String sql = "DELETE FROM ClienteVeiculo WHERE idCliente = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        }
    }

    public List<Cliente> searchByMatricula(String matricula) throws SQLException {
        String sql = "SELECT * FROM ClienteVeiculo WHERE matricula LIKE ?";
        List<Cliente> clienteVeiculos = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + matricula + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clienteVeiculos.add(new Cliente(
                    rs.getString("nome"),
                    rs.getString("residencia"),
                    rs.getString("contacto"),
                    rs.getString("matricula"),
                    rs.getString("corCarro"),
                    rs.getDouble("valorPorHora"),
                    rs.getString("tipoPagamento"),
                    rs.getBoolean("estacionado")
                ));
            }
        }
        return clienteVeiculos;
    }
    
    // Métodos para busca por outros critérios podem ser adicionados de maneira similar
}
