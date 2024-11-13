package Model.ModelDAO;

import Model.ClienteVaga;
import Model.Cliente;
import Model.Veiculo;
import Model.Vaga;
import model.DBConnect;  // Usando a classe de conexão
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteVagaDAO {

    // Método para buscar todos os clientes atualmente estacionados (sem hora de saída)
    public List<ClienteVaga> listarClientesEstacionados() {
        List<ClienteVaga> clientesEstacionados = new ArrayList<>();
        String sql = "SELECT c.idCliente, c.nome, c.residencia, c.contacto, "
                + "v.matricula, v.cor, v.modelo, v.ano, v.marca, v.tipoVeiculo, "
                + "vg.valorPorHora, vg.tipoPagamento, "
                + "cv.horaEntrada, cv.horaSaida "
                + "FROM Cliente c "
                + "JOIN ClienteVaga cv ON c.idCliente = cv.idCliente "
                + "JOIN Veiculo v ON v.matricula = cv.matricula "
                + "JOIN Vaga vg ON vg.identificadorVaga = cv.identificadorVaga "
                + "WHERE cv.horaSaida IS NULL";  // Somente clientes ainda estacionados

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Criação dos objetos Cliente, Veiculo e Vaga com os dados do ResultSet
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getBoolean("status"),
                        rs.getString("nome"),
                        rs.getString("documento"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );

                Veiculo veiculo = new Veiculo(
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getString("tipoVeiculo")
                );

                Vaga vaga = new Vaga(
                        rs.getInt("idVaga"),
                        rs.getString("identificador"),
                        rs.getDouble("valorPorHora"),
                        rs.getBoolean("isVip"),
                        rs.getBoolean("ocupado"),
                        rs.getTimestamp("horaEntrada") != null ? rs.getTimestamp("hora_entrada").toLocalDateTime() : null,
                        rs.getInt("idParque")
                );

                // Instância de ClienteVaga com os objetos criados
                LocalDateTime horaEntrada = rs.getObject("horaEntrada", LocalDateTime.class);
                LocalDateTime horaSaida = rs.getObject("horaSaida", LocalDateTime.class);
                ClienteVaga clienteVaga = new ClienteVaga(cliente, veiculo, vaga, horaEntrada, horaSaida);

                clientesEstacionados.add(clienteVaga);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes estacionados: " + e.getMessage());
        }

        return clientesEstacionados;
    }

    // Método para inserir um novo registro de ClienteVaga
    public void inserirClienteVaga(int idCliente, String identificadorVaga, String matricula, LocalDateTime horaEntrada) {
        String sql = "INSERT INTO ClienteVaga (idCliente, identificadorVaga, matricula, horaEntrada, ativo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);
            stmt.setString(3, matricula);
            stmt.setObject(4, horaEntrada);  // Inserindo LocalDateTime no banco
            stmt.setBoolean(5, true);  // Definindo como ativo
            stmt.executeUpdate();
            System.out.println("ClienteVaga inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir ClienteVaga: " + e.getMessage());
        }
    }

    // Método para buscar ClienteVaga por ID do cliente e identificador da vaga
    public ClienteVaga buscarClienteVagaPorId(int idCliente, String identificadorVaga) {
        String sql = "SELECT c.idCliente, c.nome, c.residencia, c.contacto, "
                + "v.matricula, v.cor, v.modelo, v.ano, v.marca, v.tipoVeiculo, "
                + "vg.valorPorHora, vg.tipoPagamento, "
                + "cv.horaEntrada, cv.horaSaida "
                + "FROM ClienteVaga cv "
                + "JOIN Cliente c ON c.idCliente = cv.idCliente "
                + "JOIN Veiculo v ON v.matricula = cv.matricula "
                + "JOIN Vaga vg ON vg.identificadorVaga = cv.identificadorVaga "
                + "WHERE cv.idCliente = ? AND cv.identificadorVaga = ? AND cv.ativo = true";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("idCliente"),
                            rs.getBoolean("status"),
                            rs.getString("nome"),
                            rs.getString("documento"),
                            rs.getString("telefone"),
                            rs.getString("email")
                    );
                    Veiculo veiculo = new Veiculo(
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getString("tipoVeiculo")
                
                    );
                    Vaga vaga = new Vaga(
                        rs.getInt("id_vaga"),
                        rs.getString("identificador"),
                        rs.getDouble("valor_por_hora"),
                        rs.getBoolean("is_vip"),
                        rs.getBoolean("ocupado"),
                        rs.getTimestamp("hora_entrada") != null ? rs.getTimestamp("hora_entrada").toLocalDateTime() : null,
                        rs.getInt("id_parque_relacionado")
                    );
                    LocalDateTime horaEntrada = rs.getObject("horaEntrada", LocalDateTime.class);
                    LocalDateTime horaSaida = rs.getObject("horaSaida", LocalDateTime.class);

                    return new ClienteVaga(cliente, veiculo, vaga, horaEntrada, horaSaida);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ClienteVaga: " + e.getMessage());
        }
        return null;
    }

    // Método para atualizar um registro de ClienteVaga
    public void atualizarClienteVaga(int idCliente, String identificadorVaga, LocalDateTime horaEntrada, LocalDateTime horaSaida) {
        String sql = "UPDATE ClienteVaga SET horaEntrada = ?, horaSaida = ? WHERE idCliente = ? AND identificadorVaga = ? AND ativo = true";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, horaEntrada);  // Atualizando com LocalDateTime
            stmt.setObject(2, horaSaida);
            stmt.setInt(3, idCliente);
            stmt.setString(4, identificadorVaga);
            stmt.executeUpdate();
            System.out.println("ClienteVaga atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ClienteVaga: " + e.getMessage());
        }
    }

    // Método para desativar (exclusão lógica) um registro de ClienteVaga
    public void deletarClienteVaga(int idCliente, String identificadorVaga) {
        String sql = "UPDATE ClienteVaga SET ativo = false WHERE idCliente = ? AND identificadorVaga = ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, identificadorVaga);
            stmt.executeUpdate();
            System.out.println("ClienteVaga desativado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao desativar ClienteVaga: " + e.getMessage());
        }
    }
// Método para listar todos os ClienteVaga ativos
public List<ClienteVaga> listarClientesVagasAtivos() {
        List<ClienteVaga> clientesVagasAtivos = new ArrayList<>();
        String sql = "SELECT * FROM clientevaga";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("idCliente"),
                    rs.getBoolean("ativo"),
                    rs.getString("nome"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                Veiculo veiculo = new Veiculo(
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getString("cor"),
                        rs.getString("tipoVeiculo")      
                );
                Vaga vaga = new Vaga(
                        rs.getInt("idVaga"),
                        rs.getString("identificador"),
                        rs.getDouble("valorPorHora"),
                        rs.getBoolean("isVip"),
                        rs.getBoolean("ocupado"),
                        rs.getTimestamp("horaEntrada") != null ? rs.getTimestamp("hora_entrada").toLocalDateTime() : null,
                        rs.getInt("idParque")
                );
                LocalDateTime horaEntrada = rs.getObject("horaEntrada", LocalDateTime.class);
                LocalDateTime horaSaida = rs.getObject("horaSaida", LocalDateTime.class);

                ClienteVaga clienteVaga = new ClienteVaga(cliente, veiculo, vaga, horaEntrada, horaSaida);
                clientesVagasAtivos.add(clienteVaga);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar ClienteVaga ativos: " + e.getMessage());
        }

        return clientesVagasAtivos;
    }
    
public List<ClienteVaga> listarClientesVagasMensalistasAtivos() {
    List<ClienteVaga> clientesMensalistasAtivos = new ArrayList<>();
    String sql = "SELECT c.idCliente, c.nome, c.residencia, c.contacto, "
               + "v.matricula, v.cor, v.modelo, v.marca, v.tipoVeiculo, "
               + "vg.valorPorHora, vg.tipoPagamento, "
               + "cv.horaEntrada, cv.horaSaida "
               + "FROM ClienteVaga cv "
               + "JOIN Cliente c ON c.idCliente = cv.idCliente "
               + "JOIN Veiculo v ON v.matricula = cv.matricula "
               + "JOIN Vaga vg ON vg.identificadorVaga = cv.identificadorVaga "
               + "WHERE cv.ativo = true AND vg.tipoPagamento = 'Mensalista'";

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Cliente cliente = new Cliente(
                rs.getInt("idCliente"),
                rs.getBoolean("status"),
                rs.getString("nome"),
                rs.getString("documento"),
                rs.getString("telefone"),
                rs.getString("email")
            );
            Veiculo veiculo = new Veiculo(
                rs.getString("placa"),
                rs.getString("modelo"),
                rs.getString("marca"),
                rs.getString("cor"),
                rs.getString("tipoVeiculo")
            );
            Vaga vaga = new Vaga(
                rs.getInt("id_vaga"),
                rs.getString("identificador"),
                rs.getDouble("valor_por_hora"),
                rs.getBoolean("is_vip"),
                rs.getBoolean("ocupado"),
                rs.getTimestamp("hora_entrada") != null ? rs.getTimestamp("hora_entrada").toLocalDateTime() : null,
                rs.getInt("id_parque_relacionado")
            );
            LocalDateTime horaEntrada = rs.getObject("horaEntrada", LocalDateTime.class);
            LocalDateTime horaSaida = rs.getObject("horaSaida", LocalDateTime.class);

            ClienteVaga clienteVaga = new ClienteVaga(cliente, veiculo, vaga, horaEntrada, horaSaida);
            clientesMensalistasAtivos.add(clienteVaga);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar ClienteVaga mensalistas ativos: " + e.getMessage());
    }

    return clientesMensalistasAtivos;
}
}
