package Model.ModelDAO;

import Model.Cliente;
import model.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Inserir cliente (ativo por padrão)
    public void inserirCliente(int idPessoa, boolean status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection(); // Obtém conexão
            String sql = "INSERT INTO Cliente (idPessoa, status, ativo) VALUES (?, ?, TRUE)"; // Cliente ativo por padrão
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.setBoolean(2, status); // Status é booleano
            stmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Buscar cliente por ID (somente clientes ativos)
    public Cliente buscarCliente(int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            conn = DBConnect.getConnection(); // Obtém conexão
            String sql = "SELECT * FROM Cliente WHERE idCliente = ? AND ativo = TRUE"; // Somente clientes ativos
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setIdPessoa(rs.getInt("idPessoa"));
                cliente.setStatus(rs.getBoolean("status")); // Status como boolean
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            DBConnect.closeConnection(conn);
        }
        return cliente;
    }

    // Atualizar cliente
    public void atualizarCliente(int idCliente, boolean status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection(); // Obtém conexão
            String sql = "UPDATE Cliente SET status = ? WHERE idCliente = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, status); // Status booleano
            stmt.setInt(2, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Exclusão lógica (marca o cliente como inativo)
    public void excluirClienteLogicamente(int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection(); // Obtém conexão
            String sql = "UPDATE Cliente SET ativo = FALSE WHERE idCliente = ?"; // Marcar cliente como inativo
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente marcado como inativo com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao marcar cliente como inativo: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Listar todos os clientes ativos
    public List<Cliente> listarClientes() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = DBConnect.getConnection(); // Obtém conexão
            String sql = "SELECT * FROM Cliente WHERE ativo = TRUE"; // Somente clientes ativos
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setIdPessoa(rs.getInt("idPessoa"));
                cliente.setStatus(rs.getBoolean("status")); // Status como boolean
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
        return clientes;
    }
}
