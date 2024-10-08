package Model.ModelDAO;
import Model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public void create(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (nome, usuario, senha, numeroBI, NUIT, Email, departamento, tipo, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getNumeroBI());
            stmt.setString(5, funcionario.getNUIT());
            stmt.setString(6, funcionario.getEmail());
            stmt.setString(7, funcionario.getDepartamento());
            stmt.setString(8, funcionario.getTipo());
            stmt.setBoolean(9, funcionario.isAtivo());
            stmt.executeUpdate();
        }
    }

    public Funcionario read(int idFuncionario) throws SQLException {
        String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Funcionario(
                    rs.getString("nome"),
                    rs.getString("usuario"),
                    rs.getString("senha"),
                    rs.getString("numeroBI"),
                    rs.getString("NUIT"),
                    rs.getString("Email"),
                    rs.getString("departamento"),
                    rs.getString("tipo")
                );
            }
        }
        return null;
    }

    public void update(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE Funcionario SET nome = ?, usuario = ?, senha = ?, numeroBI = ?, NUIT = ?, Email = ?, departamento = ?, tipo = ?, ativo = ? WHERE idFuncionario = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getNumeroBI());
            stmt.setString(5, funcionario.getNUIT());
            stmt.setString(6, funcionario.getEmail());
            stmt.setString(7, funcionario.getDepartamento());
            stmt.setString(8, funcionario.getTipo());
            stmt.setBoolean(9, funcionario.isAtivo());
            stmt.setInt(10, funcionario.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int idFuncionario) throws SQLException {
        String sql = "DELETE FROM Funcionario WHERE idFuncionario = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            stmt.executeUpdate();
        }
    }

    public List<Funcionario> searchByName(String nome) throws SQLException {
        String sql = "SELECT * FROM Funcionario WHERE nome LIKE ?";
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                funcionarios.add(new Funcionario(
                    rs.getString("nome"),
                    rs.getString("usuario"),
                    rs.getString("senha"),
                    rs.getString("numeroBI"),
                    rs.getString("NUIT"),
                    rs.getString("Email"),
                    rs.getString("departamento"),
                    rs.getString("tipo")
                ));
            }
        }
        return funcionarios;
    }
    
    // Métodos para busca por outros critérios podem ser adicionados de maneira similar
}
