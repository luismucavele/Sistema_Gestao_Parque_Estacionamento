package Model.ModelDAO;

import Model.Funcionario;  // Classe Funcionario no pacote Model
import model.DBConnect;     // Classe de conexão com o banco de dados
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // Inserir funcionário
    public void inserirFuncionario(String nome, String cargo, double salario, String usuario, String senha, String documento, String telefone, String sexo, String email, String bairro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();
            String sql = "INSERT INTO funcionarios (nome, cargo, salario, usuario, senha, documento, telefone, sexo, email, bairro, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cargo);
            stmt.setDouble(3, salario);
            stmt.setString(4, usuario.trim());
            stmt.setString(5, senha.trim());
            stmt.setString(6, documento);
            stmt.setString(7, telefone);
            stmt.setString(8, sexo);
            stmt.setString(9, email);
            stmt.setString(10, bairro);
            stmt.setBoolean(11, true); // status ativo por padrão
            stmt.executeUpdate();
            System.out.println("Funcionário inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);
        }
    }

    // Autenticar funcionário
    public Funcionario autenticarFuncionario(String usuario, String senha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM funcionarios WHERE usuario = ? AND senha = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.trim());
            stmt.setString(2, senha.trim());
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cargo"),
                    rs.getDouble("salario"),
                    rs.getString("usuario"),
                    rs.getString("senha"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("sexo"),
                    rs.getString("email"),
                    rs.getString("bairro"),
                    rs.getBoolean("status")
                );
            } else {
                System.out.println("Usuário ou senha inválidos.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar funcionário: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);
        }
        return funcionario;
    }

    // Carregar lista de funcionários
    public List<Funcionario> carregarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (PreparedStatement stmt = DBConnect.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cargo"),
                    rs.getDouble("salario"),
                    rs.getString("usuario"),
                    rs.getString("senha"),
                    rs.getString("documento"),
                    rs.getString("telefone"),
                    rs.getString("sexo"),
                    rs.getString("email"),
                    rs.getString("bairro"),
                    rs.getBoolean("status")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar funcionários: " + e.getMessage());
        }

        return funcionarios;
    }

    // Buscar funcionário por ID
   public Funcionario buscarFuncionario(int idFuncionario) {
    Funcionario funcionario = null;
    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM funcionarios WHERE id = ?")) { // Alterar aqui para o nome correto
         
        stmt.setInt(1, idFuncionario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            funcionario = new Funcionario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cargo"),
                rs.getDouble("salario"),
                rs.getString("usuario"),
                rs.getString("senha"),
                rs.getString("documento"),
                rs.getString("telefone"),
                rs.getString("sexo"),
                rs.getString("email"),
                rs.getString("bairro"),
                rs.getBoolean("status")
            );
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar funcionário: " + e.getMessage());
    } 

    return funcionario;
}



   // Atualizar funcionário
public void atualizarFuncionario(Funcionario funcionario) {
    if (funcionario == null) {
        System.err.println("O funcionário fornecido é nulo.");
        return;
    }

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = DBConnect.getConnection();
        String sql = "UPDATE funcionarios SET nome = ?, cargo = ?, salario = ?, usuario = ?, senha = ?, documento = ?, telefone = ?, sexo = ?, email = ?, bairro = ?, status = ? WHERE id = ?";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getCargo());
        stmt.setDouble(3, funcionario.getSalario());
        stmt.setString(4, funcionario.getUsuario());
        stmt.setString(5, funcionario.getSenha());
        stmt.setString(6, funcionario.getDocumento());
        stmt.setString(7, funcionario.getTelefone());
        stmt.setString(8, funcionario.getSexo());
        stmt.setString(9, funcionario.getEmail());
        stmt.setString(10, funcionario.getResidencia());
        stmt.setBoolean(11, funcionario.isAtivo());
        stmt.setInt(12, funcionario.getIdFuncionario());

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Funcionário atualizado com sucesso.");
        } else {
            System.out.println("Nenhum funcionário foi encontrado com o ID especificado.");
        }
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
    } finally {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) DBConnect.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}


    // Desativar funcionário (remoção lógica)
public void apagarLogicoFuncionario(int id) throws SQLException {
    String sql = "UPDATE Funcionarios SET status = false WHERE id = ?";
    Connection conn = null;

    try {
        conn = DBConnect.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    } finally {
        if (conn != null) {
            DBConnect.closeConnection(conn);
        }
    }
}


    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
    List<Funcionario> funcionarios = new ArrayList<>();
    String sql = "SELECT * FROM funcionarios WHERE status = true"; // Apenas funcionários ativos

    try (Connection conn = DBConnect.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Funcionario funcionario = new Funcionario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cargo"),
                rs.getDouble("salario"),
                rs.getString("usuario"),
                rs.getString("senha"),
                rs.getString("documento"),
                rs.getString("telefone"),
                rs.getString("sexo"),
                rs.getString("email"),
                rs.getString("bairro"),
                rs.getBoolean("status")
            );
            funcionarios.add(funcionario);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar funcionários: " + e.getMessage());
    }

    return funcionarios;
  }

}
