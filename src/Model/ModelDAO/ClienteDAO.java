package Model.ModelDAO;

import Model.Cliente;
import model.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Inserir cliente (ativo por padrão)
    public void inserirCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection(); // Obtém conexão
            String sql = "INSERT INTO Cliente (idCliente, nome, documento, telefone, email, status) VALUES (?, ?, ?, ?, ?, TRUE)"; // Cliente ativo por padrão
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente()); // O ID pode ser um campo autoincrementado ou fornecido externamente
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getDocumento());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
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
            String sql = "SELECT * FROM Cliente WHERE idCliente = ? AND status = TRUE"; // Somente clientes ativos
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                    rs.getInt("idCliente"),
                    true, // Status ativo por padrão, já que estamos buscando apenas clientes ativos
                    rs.getString("nome"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
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
            DBConnect.closeConnection(conn); // Fecha a conexão
        }
        return cliente;
    }

    // Atualizar cliente
    public void atualizarCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection(); // Obtém conexão
            String sql = "UPDATE Cliente SET nome = ?, documento = ?, telefone = ?, email = ?, status = ? WHERE idCliente = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setBoolean(5, cliente.isStatus());
            stmt.setInt(6, cliente.getIdCliente());
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
            String sql = "UPDATE Cliente SET status = FALSE WHERE idCliente = ?"; // Marcar cliente como inativo
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
            String sql = "SELECT * FROM Cliente WHERE status = TRUE"; // Somente clientes ativos
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("idCliente"),
                    true, // Status ativo por padrão
                    rs.getString("nome"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
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
