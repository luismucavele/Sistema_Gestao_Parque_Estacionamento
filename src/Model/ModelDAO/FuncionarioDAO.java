package Model.ModelDAO;

import Model.Funcionario;  // Assumindo que há uma classe Funcionario no pacote Model
import model.DBConnect;     // Para usar a classe DBConnect
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // Inserir funcionário
    public void inserirFuncionario(int idPessoa, String cargo, double salario, String usuario, String senha, boolean ativo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém conexão
            String sql = "INSERT INTO Funcionario (idPessoa, cargo, salario, usuario, senha, ativo) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.setString(2, cargo);
            stmt.setDouble(3, salario);
            stmt.setString(4, usuario);
            stmt.setString(5, senha);
            stmt.setBoolean(6, ativo);
            stmt.executeUpdate();
            System.out.println("Funcionário inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Buscar funcionário por ID
    public Funcionario buscarFuncionario(int idFuncionario) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Funcionario funcionario = null;
    try {
        conn = DBConnect.getConnection();  // Obtém conexão
        String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idFuncionario);
        rs = stmt.executeQuery();

        if (rs.next()) {
            funcionario = new Funcionario(
                rs.getInt("idFuncionario"),
                rs.getString("nome"),        // Assumindo que 'nome' está na tabela
                rs.getString("documento"),   // Assumindo que 'documento' está na tabela
                rs.getString("telefone"),    // Assumindo que 'telefone' está na tabela
                rs.getString("email"),       // Assumindo que 'email' está na tabela
                rs.getString("cargo"),
                rs.getDouble("salario"),
                rs.getString("usuario"),
                rs.getString("senha"),
                rs.getBoolean("ativo")
            );
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar funcionário: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
        DBConnect.closeConnection(conn);  // Fecha a conexão
    }
    return funcionario;
}
    // Atualizar funcionário
    public void atualizarFuncionario(int idFuncionario, String cargo, double salario, String usuario, String senha, boolean ativo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém conexão
            String sql = "UPDATE Funcionario SET cargo = ?, salario = ?, usuario = ?, senha = ?, ativo = ? WHERE idFuncionario = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cargo);
            stmt.setDouble(2, salario);
            stmt.setString(3, usuario);
            stmt.setString(4, senha);
            stmt.setBoolean(5, ativo);
            stmt.setInt(6, idFuncionario);
            stmt.executeUpdate();
            System.out.println("Funcionário atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Deletar funcionário
    public void deletarFuncionario(int idFuncionario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém conexão
            String sql = "DELETE FROM Funcionario WHERE idFuncionario = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            stmt.executeUpdate();
            System.out.println("Funcionário deletado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar funcionário: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();  // Obtém conexão
            String sql = "SELECT * FROM Funcionario";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                    rs.getInt("idFuncionario"),
                    rs.getString("nome"),        // Assumindo que 'nome' está na tabela
                    rs.getString("documento"),   // Assumindo que 'documento' está na tabela
                    rs.getString("telefone"),    // Assumindo que 'telefone' está na tabela
                    rs.getString("email"),       // Assumindo que 'email' está na tabela
                    rs.getString("cargo"),
                    rs.getDouble("salario"),
                    rs.getString("usuario"),
                    rs.getString("senha"),
                    rs.getBoolean("ativo")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
        return funcionarios;
    }

    // Buscar funcionários por cargo
    public List<Funcionario> buscarFuncionariosPorCargo(String cargo) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Funcionario> funcionarios = new ArrayList<>();
    try {
        conn = DBConnect.getConnection();  // Obtém conexão
        String sql = "SELECT * FROM Funcionario WHERE cargo = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, cargo);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Funcionario funcionario = new Funcionario(
                rs.getInt("idFuncionario"),
                rs.getString("nome"),        // Assumindo que 'nome' está na tabela
                rs.getString("documento"),   // Assumindo que 'documento' está na tabela
                rs.getString("telefone"),    // Assumindo que 'telefone' está na tabela
                rs.getString("email"),       // Assumindo que 'email' está na tabela
                rs.getString("cargo"),
                rs.getDouble("salario"),
                rs.getString("usuario"),
                rs.getString("senha"),
                rs.getBoolean("ativo")
            );
            funcionarios.add(funcionario);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar funcionários por cargo: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
        DBConnect.closeConnection(conn);  // Fecha a conexão
    }
    return funcionarios;
    }
}