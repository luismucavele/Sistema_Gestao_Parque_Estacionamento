package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
    private static Connection connection;

    static {
        try {
            // Configurar a conexão com o banco de dados
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ou o driver correto para o seu banco
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_de_p_estacionamento", "root", "");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do banco de dados: " + e.getMessage());
            throw new RuntimeException("Erro ao carregar o driver do banco de dados", e);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_de_p_estacionamento", "root", "");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
        return connection;
    }

    
    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}