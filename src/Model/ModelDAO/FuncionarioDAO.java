package Model.ModelDAO;

import Model.Funcionario;  // Assumindo que há uma classe Funcionario no pacote Model
import model.DBConnect;     // Para usar a classe DBConnect
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // Inserir funcionário
    public void inserirFuncionario(String cargo, double salario, String usuario, String senha, boolean ativo, String documento, String telefone, String sexo, String email, String bairro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém a conexão
            String sql = "INSERT INTO Funcionario (cargo, salario, usuario, senha, ativo, documento, telefone, sexo, email, Residencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cargo);
            stmt.setDouble(2, salario);
            stmt.setString(3, usuario);
            stmt.setString(4, senha);
            stmt.setBoolean(5, ativo);
            stmt.setString(6, documento);
            stmt.setString(7, telefone);
            stmt.setString(8, sexo);
            stmt.setString(9, email);
            stmt.setString(10, bairro);
            stmt.executeUpdate();
            System.out.println("Funcionário inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        } finally {
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
    }

    // Método para autenticar um funcionário com base no usuário e senha
    public Funcionario autenticarFuncionario(String usuario, String senha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;
        try {
            conn = DBConnect.getConnection(); // Conecta ao banco
            String sql = "SELECT * FROM Funcionario WHERE usuario = ? AND senha = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario(
                        rs.getInt("idFuncionario"),
                        rs.getString("nome"), // Assumindo que 'nome' está na tabela
                        rs.getString("documento"), // Assumindo que 'documento' está na tabela
                        rs.getString("telefone"), // Assumindo que 'telefone' está na tabela
                        rs.getString("email"), // Assumindo que 'email' está na tabela
                        rs.getString("cargo"),
                        rs.getDouble("salario"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo"),
                        rs.getString("residencia"),
                        rs.getString("sexo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar funcionário: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            DBConnect.closeConnection(conn); // Fecha a conexão
        }
        return funcionario;
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
                        rs.getString("nome"), // Assumindo que 'nome' está na tabela
                        rs.getString("documento"), // Assumindo que 'documento' está na tabela
                        rs.getString("telefone"), // Assumindo que 'telefone' está na tabela
                        rs.getString("email"), // Assumindo que 'email' está na tabela
                        rs.getString("cargo"),
                        rs.getDouble("salario"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo"),
                        rs.getString("residencia"),
                        rs.getString("sexo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
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
    public void apagarLogicoFuncionario(int idFuncionario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnect.getConnection();  // Obtém conexão
            String sql = "UPDATE Funcionario SET ativo = FALSE WHERE idFuncionario = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            stmt.executeUpdate();
            System.out.println("Funcionário marcado como inativo com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao marcar funcionário como inativo: " + e.getMessage());
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
                        rs.getString("nome"), // Assumindo que 'nome' está na tabela
                        rs.getString("documento"), // Assumindo que 'documento' está na tabela
                        rs.getString("telefone"), // Assumindo que 'telefone' está na tabela
                        rs.getString("email"), // Assumindo que 'email' está na tabela
                        rs.getString("cargo"),
                        rs.getDouble("salario"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo"),
                        rs.getString("residencia"),
                        rs.getString("sexo")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
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
                        rs.getString("nome"), // Assumindo que 'nome' está na tabela
                        rs.getString("documento"), // Assumindo que 'documento' está na tabela
                        rs.getString("telefone"), // Assumindo que 'telefone' está na tabela
                        rs.getString("email"), // Assumindo que 'email' está na tabela
                        rs.getString("cargo"),
                        rs.getDouble("salario"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo"),
                        rs.getString("residencia"),
                        rs.getString("sexo")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionários por cargo: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
        return funcionarios;
    }

    public List<Funcionario> listarFuncionariosAtivos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();  // Obtém conexão
            String sql = "SELECT * FROM Funcionario WHERE ativo = TRUE";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getInt("idFuncionario"),
                        rs.getString("nome"), // Assumindo que 'nome' está na tabela
                        rs.getString("documento"), // Assumindo que 'documento' está na tabela
                        rs.getString("telefone"), // Assumindo que 'telefone' está na tabela
                        rs.getString("email"), // Assumindo que 'email' está na tabela
                        rs.getString("cargo"),
                        rs.getDouble("salario"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo"),
                        rs.getString("residencia"),
                        rs.getString("sexo")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
        return funcionarios;
    }

    public List<Funcionario> listarFuncionariosInativos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();  // Obtém conexão
            String sql = "SELECT * FROM Funcionario WHERE ativo = FALSE";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getInt("idFuncionario"),
                        rs.getString("nome"), // Assumindo que 'nome' está na tabela
                        rs.getString("documento"), // Assumindo que 'documento' está na tabela
                        rs.getString("telefone"), // Assumindo que 'telefone' está na tabela
                        rs.getString("email"), // Assumindo que 'email' está na tabela
                        rs.getString("cargo"),
                        rs.getDouble("salario"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo"),
                        rs.getString("residencia"),
                        rs.getString("sexo")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários inativos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            DBConnect.closeConnection(conn);  // Fecha a conexão
        }
        return funcionarios;
    }

}
