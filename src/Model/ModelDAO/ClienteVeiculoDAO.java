package model.dao;

import model.ClienteVeiculo;
import model.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteVeiculoDAO {

    // Inserir novo cliente e veículo
    public void inserirClienteVeiculo(ClienteVeiculo cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "INSERT INTO ClienteVeiculo (nome, residencia, contacto, matricula, corCarro, tipoPagamento, valorPorHora) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getResidencia());
            stmt.setString(3, cliente.getContacto());
            stmt.setString(4, cliente.getMatricula());
            stmt.setString(5, cliente.getCorCarro());
            stmt.setString(6, cliente.getTipoPagamento());
            stmt.setDouble(7, cliente.getValorPorHora());
            stmt.executeUpdate();
            System.out.println("Cliente e veículo inseridos com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente e veículo: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);
        }
    }

    // Listar todos os clientes
    public List<ClienteVeiculo> listarClientes() {
        List<ClienteVeiculo> clientes = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM ClienteVeiculo";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ClienteVeiculo cliente = new ClienteVeiculo(
                    rs.getInt("idCliente"),
                    rs.getString("nome"),
                    rs.getString("residencia"),
                    rs.getString("contacto"),
                    rs.getString("matricula"),
                    rs.getString("corCarro"),
                    rs.getString("tipoPagamento"),
                    rs.getDouble("valorPorHora")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);
        }
        return clientes;
    }

    // Atualizar dados de um cliente
    public void atualizarClienteVeiculo(ClienteVeiculo cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "UPDATE ClienteVeiculo SET nome = ?, residencia = ?, contacto = ?, matricula = ?, corCarro = ?, tipoPagamento = ?, valorPorHora = ? WHERE idCliente = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getResidencia());
            stmt.setString(3, cliente.getContacto());
            stmt.setString(4, cliente.getMatricula());
            stmt.setString(5, cliente.getCorCarro());
            stmt.setString(6, cliente.getTipoPagamento());
            stmt.setDouble(7, cliente.getValorPorHora());
            stmt.setInt(8, cliente.getIdCliente());
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);
        }
    }

    // Deletar cliente
    public void deletarCliente(int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "DELETE FROM ClienteVeiculo WHERE idCliente = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente deletado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);
        }
    }
}