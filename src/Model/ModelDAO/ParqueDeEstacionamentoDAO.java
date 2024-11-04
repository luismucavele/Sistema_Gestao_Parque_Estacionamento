package Model.ModelDAO;
import Model.ParqueDeEstacionamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DBConnect;

public class ParqueDeEstacionamentoDAO {

    // Método para inserir parque de estacionamento no banco de dados
public void atualizarParqueDeEstacionamento(int idParque, String nome, String endereco, String capacidade, String vagasDisponiveis) throws Exception {
    String sql = "UPDATE parque_estacionamento SET nome = ?, endereco = ?, capacidade_total = ?, vagas_disponiveis = ? WHERE id_parque = ?";

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nome);
        stmt.setString(2, endereco);
        stmt.setInt(3, Integer.parseInt(capacidade));
        stmt.setInt(4, Integer.parseInt(vagasDisponiveis));
        stmt.setInt(5, idParque);
        stmt.executeUpdate();
    }
}

public void inserirParqueDeEstacionamento(String nome, String endereco, String capacidade, String vagasDisponiveis) throws Exception {
    String sql = "INSERT INTO parque_estacionamento (nome, endereco, capacidade_total, vagas_disponiveis,  ativo) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nome);
        stmt.setString(2, endereco);
        stmt.setInt(3, Integer.parseInt(capacidade));
        stmt.setInt(4, Integer.parseInt(vagasDisponiveis));
        stmt.setBoolean(5, true);
        stmt.executeUpdate();
    }
}

    // Método para listar todos os parques ativos
    public List<ParqueDeEstacionamento> listarParquesAtivos() throws Exception {
        List<ParqueDeEstacionamento> parques = new ArrayList<>();
        String sql = "SELECT * FROM parque_estacionamento WHERE ativo = 1";

        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ParqueDeEstacionamento parque = new ParqueDeEstacionamento();
                parque.setIdParque(rs.getInt("id_parque"));
                parque.setNome(rs.getString("nome"));
                parque.setEndereco(rs.getString("endereco"));
                parque.setCapacidadeTotal(rs.getInt("capacidade_total"));
                parque.setVagasDisponiveis(rs.getInt("vagas_disponiveis"));
                parques.add(parque);
            }
        }
        return parques;
    }
    
//    public void atualizarParqueDeEstacionamento(int idParque, String nome, String endereco, String capacidade, String vagasDisponiveis) throws Exception {
//    String sql = "UPDATE parque_estacionamento SET nome = ?, endereco = ?, capacidade_total = ?, vagas_disponiveis = ? WHERE id_parque = ?";
//
//    try (Connection conn = DBConnect.getConnection();
//         PreparedStatement stmt = conn.prepareStatement(sql)) {
//        stmt.setString(1, nome);
//        stmt.setString(2, endereco);
//        stmt.setInt(3, Integer.parseInt(capacidade));
//        stmt.setInt(4, Integer.parseInt(vagasDisponiveis));
//        stmt.setInt(5, idParque);
//        stmt.executeUpdate();
//    }
//}
}
