package Model.ModelDAO;

import Model.Cliente;
import Model.Cliente;
import model.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void inserirCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, documento, telefone, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefone());
            stmt.setBoolean(4, cliente.isStatus());
            stmt.executeUpdate();
        }
    }

    public Cliente buscarCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE idCliente = ?";
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("idCliente"),
                    rs.getBoolean("status"),
                    rs.getString("nome"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                    
                );
            }
        }
        return null;
    }

    public List<Cliente> listarClientes() throws SQLException {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("idCliente"),
                    rs.getBoolean("status"),
                    rs.getString("nome"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        }
        return clientes;
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, documento = ?, telefone = ?, status = ? WHERE idCliente = ?";
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefone());
            stmt.setBoolean(4, cliente.isStatus());
            stmt.setInt(5, cliente.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public void excluirCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE idCliente = ?";
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        }
    }
}
