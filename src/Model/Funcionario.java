package Model;

public class Funcionario extends Pessoa {
    private int idFuncionario;
    private String cargo;
    private double salario;
    private String usuario;
    private String senha;
    private boolean status;
    private String residencia;
    private String sexo;

    // Construtor completo
    public Funcionario(int idFuncionario, String nome, String cargo, double salario,String usuario, String senha, String documento, String telefone, String sexo, String email, String residencia, boolean status) {
        super(nome, documento, telefone, email);
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
        this.salario = salario;
        this.usuario = usuario;
        this.senha = senha;
        this.status = status;
        this.residencia = residencia;
        this.sexo = sexo;
    }

    // Construtor sem argumentos
     public Funcionario() {
        super();
    }

    // Getters e Setters
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
        return status;
    }

    public void setAtivo(boolean ativo) {
        this.status = ativo;
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

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    // Métodos adicionais
    public void atualizarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public void desativarFuncionario() {
        this.status = false;
    }

    public void ativarFuncionario() {
        this.status = true;
    }

    public void aumentarSalario(double porcentagem) {
        this.salario += this.salario * porcentagem / 100;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + getNome() + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", ativo=" + status +
                ", residencia='" + residencia + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
