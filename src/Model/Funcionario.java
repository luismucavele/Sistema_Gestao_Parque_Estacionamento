package Model;

public class Funcionario extends Pessoa {
    private int idFuncionario;
    private String cargo;
    private double salario;
    private String usuario;
    private String senha;
    private boolean ativo;
    private String residencia;
    private String sexo;
    
    
    

    // Construtor completo
    public Funcionario(int idFuncionario, String nome, String documento, String telefone, String email, String cargo, double salario, String usuario, String senha, boolean ativo, String residencia, String sexo) {
        super(nome, documento, telefone, email);
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
        this.salario = salario;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
        this.residencia = residencia;
        this.sexo = sexo;
        
    }
    
    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getSexo() {
        return sexo;
    }

    // Getters e Setters
    public void setSexo(String sexo) {    
        this.sexo = sexo;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Métodos adicionais
    public void atualizarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public void desativarFuncionario() {
        this.ativo = false;
    }

    public void ativarFuncionario() {
        this.ativo = true;
    }

    public void aumentarSalario(double porcentagem) {
        this.salario += this.salario * porcentagem / 100;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", ativo=" + ativo +
                ", residencia" + residencia +
                ", sexo=" + sexo +
                
                '}';
    }
}
