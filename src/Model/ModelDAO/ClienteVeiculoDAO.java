package Model.ModelDAO;

import Model.ClienteVeiculo;
import Model.EspacoEstacionamento;
import Model.Cliente;
import Model.Veiculo;
import model.DBConnect;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteVeiculoDAO {

    public void registrarEntrada(ClienteVeiculo clienteVeiculo) throws SQLException {
        String sql = "INSERT INTO cliente_veiculo (clienteId, veiculoId, horaEntrada, estacionado, espacoEstacionamentoId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql)) {
            stmt.setInt(1, clienteVeiculo.getCliente().getIdCliente());
            stmt.setString(2, clienteVeiculo.getVeiculo().getPlaca());
            stmt.setObject(3, clienteVeiculo.getHoraEntrada());  // Usando setObject para LocalDateTime
            stmt.setBoolean(4, clienteVeiculo.isEstacionado());
            stmt.setString(5, clienteVeiculo.getEspacoEstacionamento().getIdentificador());
            stmt.executeUpdate();
        }
    }

    public void registrarSaida(int clienteId, LocalDateTime horaSaida) throws SQLException {
        String sql = "UPDATE cliente_veiculo SET horaSaida = ?, estacionado = false WHERE clienteId = ? AND estacionado = true";
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql)) {
            stmt.setObject(1, horaSaida);  // Usando setObject para LocalDateTime
            stmt.setInt(2, clienteId);
            stmt.executeUpdate();
        }
    }

    public List<ClienteVeiculo> listarEntradas() throws SQLException {
        String sql = "SELECT * FROM cliente_veiculo";
        List<ClienteVeiculo> entradas = new ArrayList<>();
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new ClienteDAO().buscarCliente(rs.getInt("clienteId"));
                Veiculo veiculo = new VeiculoDAO().buscarVeiculo(rs.getString("veiculoId"));
                EspacoEstacionamento espaco = new EspacoEstacionamentoDAO().read(rs.getString("espacoEstacionamentoId"));

                LocalDateTime horaEntrada = rs.getObject("horaEntrada", LocalDateTime.class);
                LocalDateTime horaSaida = rs.getObject("horaSaida", LocalDateTime.class);  // Hora de saída pode ser nula

                ClienteVeiculo clienteVeiculo = new ClienteVeiculo(cliente, veiculo, horaEntrada, espaco);
                clienteVeiculo.setHoraSaida(horaSaida);  // Definindo a hora de saída, se houver
                clienteVeiculo.setEstacionado(rs.getBoolean("estacionado"));

                entradas.add(clienteVeiculo);
            }
        }
        return entradas;
    }

    public ClienteVeiculo read(int clienteVeiculoId) throws SQLException {
        String sql = "SELECT * FROM cliente_veiculo WHERE clienteId = ?";
        try (PreparedStatement stmt = DBConnect.prepareStatement(sql)) {
            stmt.setInt(1, clienteVeiculoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new ClienteDAO().buscarCliente(rs.getInt("clienteId"));
                Veiculo veiculo = new VeiculoDAO().buscarVeiculo(rs.getString("veiculoId"));
                EspacoEstacionamento espaco = new EspacoEstacionamentoDAO().read(rs.getString("espacoEstacionamentoId"));

                LocalDateTime horaEntrada = rs.getObject("horaEntrada", LocalDateTime.class);
                LocalDateTime horaSaida = rs.getObject("horaSaida", LocalDateTime.class);

                ClienteVeiculo clienteVeiculo = new ClienteVeiculo(cliente, veiculo, horaEntrada, espaco);
                clienteVeiculo.setHoraSaida(horaSaida);
                clienteVeiculo.setEstacionado(rs.getBoolean("estacionado"));

                return clienteVeiculo;
            }
        }
        return null;
    }
}
