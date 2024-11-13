package Model.ModelDAO;

import Model.ClienteMensalista;
import Model.Veiculo;
import model.DBConnect;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteMensalistaDAO {

    // Inserir um novo cliente mensalista com o veículo
    public void inserirClienteMensalista(int idCliente, Date prazoSaida, double taxaMensal, Date dataRegistro, Veiculo veiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO ClienteMensalista (idCliente, prazoSaida, taxaMensal, dataRegistro, ativo, placaVeiculo) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setDate(2, prazoSaida);
            stmt.setDouble(3, taxaMensal);
            stmt.setDate(4, dataRegistro);
            stmt.setBoolean(5, true);  // Cliente ativo ao ser inserido
            stmt.setString(6, veiculo.getPlaca()); // Associa o veículo
            stmt.executeUpdate();
            System.out.println("Cliente mensalista e veículo inseridos com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente mensalista: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, null);  // Fecha a conexão
        }
    }

    // Atualizar os dados de um cliente mensalista e seu veículo
 public void atualizarClienteMensalista(ClienteMensalista clienteMensalista) throws SQLException {
    String sql = "UPDATE ClienteMensalista SET estacionado = ?, dataSaida = ?, multa = ? WHERE idCliente = ?";

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setBoolean(1, clienteMensalista.isEstacionado());
        stmt.setDate(2, Date.valueOf(clienteMensalista.getPrazoSaida()));
        stmt.setDouble(3, clienteMensalista.getMulta());
        stmt.setInt(4, clienteMensalista.getIdCliente());

        stmt.executeUpdate();
    }
}

    // Buscar cliente mensalista por ID e retornar objeto ClienteMensalista com Veiculo
    public ClienteMensalista buscarClienteMensalista(int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteMensalista clienteMensalista = null;

        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "SELECT * FROM ClienteMensalista WHERE idCliente = ? AND ativo = true";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                String documento = rs.getString("documento");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                LocalDate prazoSaida = rs.getDate("prazoSaida").toLocalDate();
                double taxaMensal = rs.getDouble("taxaMensal");
                LocalDate dataRegistro = rs.getDate("dataRegistro").toLocalDate();
                boolean estacionado = rs.getBoolean("estacionado");

                // Recuperando os dados do veículo associado
                String placa = rs.getString("placaVeiculo");
                Veiculo veiculo = buscarVeiculo(placa);

                // Cria a instância de ClienteMensalista com o Veiculo
                clienteMensalista = new ClienteMensalista(id, nome, documento, telefone, email, prazoSaida, taxaMensal, dataRegistro, veiculo);
                clienteMensalista.setEstacionado(estacionado);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente mensalista: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);  // Fecha a conexão
        }

        return clienteMensalista;
    }

    // Método para ler todos os clientes mensalistas ativos
    public List<ClienteMensalista> read(int idCliente) {
        List<ClienteMensalista> clientesMensalistas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getConnection(); // Obtém a conexão
            String sql = "SELECT * FROM ClienteMensalista WHERE ativo = true";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                String documento = rs.getString("documento");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                LocalDate prazoSaida = rs.getDate("prazoSaida").toLocalDate();
                double taxaMensal = rs.getDouble("taxaMensal");
                LocalDate dataRegistro = rs.getDate("dataRegistro").toLocalDate();
                boolean estacionado = rs.getBoolean("estacionado");

                // Buscar o veículo associado
                String placaVeiculo = rs.getString("placaVeiculo");
                Veiculo veiculo = buscarVeiculo(placaVeiculo);

                // Cria uma instância de ClienteMensalista
                ClienteMensalista cliente = new ClienteMensalista(id, nome, documento, telefone, email, prazoSaida, taxaMensal, dataRegistro, veiculo);
                cliente.setEstacionado(estacionado);

                // Adiciona o cliente à lista
                clientesMensalistas.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler clientes mensalistas: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);  // Fecha a conexão
        }

        return clientesMensalistas;
    }

    // Método auxiliar para buscar Veiculo associado
    private Veiculo buscarVeiculo(String placa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Veiculo veiculo = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM Veiculo WHERE placa = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            rs = stmt.executeQuery();

            if (rs.next()) {
                veiculo = new Veiculo(
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getString("tipoVeiculo")
                );
                veiculo.setEstacionado(rs.getBoolean("estacionado"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);  // Fecha a conexão
        }

        return veiculo;
    }

    // Método auxiliar para fechar recursos
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
    
    
    public ClienteMensalista read1(int idCliente) throws SQLException {
    ClienteMensalista cliente = null;
    String sql = "SELECT * FROM ClienteMensalista WHERE idCliente = ?";
    
    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, idCliente);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            int id = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                String documento = rs.getString("documento");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                LocalDate prazoSaida = rs.getDate("prazoSaida").toLocalDate();
                double taxaMensal = rs.getDouble("taxaMensal");
                LocalDate dataRegistro = rs.getDate("dataRegistro").toLocalDate();
                boolean estacionado = rs.getBoolean("estacionado");

                // Buscar o veículo associado
                String placaVeiculo = rs.getString("placaVeiculo");
                Veiculo veiculo = buscarVeiculo(placaVeiculo);
        }
    }
    return cliente;
}

}
