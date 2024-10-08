package Model;

public class Funcionario extends Pessoa {
    private static int contador = 1;
    private int idFuncionario;
    private String usuario;
    private String senha;
    private String numeroBI;
    private String NUIT;
    private String Email;
    private String departamento;
    private String tipo; 
    private boolean ativo;

    // Construtor
    public Funcionario(String nome, String contacto, String residencia, String usuario, String senha, String numeroBI, String NUIT, String Email, String departamento, String tipo) {
        super(nome, contacto, residencia);
        this.idFuncionario = contador++;
        this.usuario = usuario;
        this.senha = senha;
        this.numeroBI = numeroBI;
        this.NUIT = NUIT;
        this.Email = Email;
        this.departamento = departamento;
        this.tipo = tipo;
        this.ativo = true;
    }

    // Getters e setters específicos de Funcionario
    public int getIdFuncionario() {
        return idFuncionario;
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

    // Sobrescrita do método exibirDetalhes
    @Override
    public String exibirDetalhes() {
        return String.format(
            "ID Funcionario: %d\nNome: %s\nContacto: %s\nResidência: %s\nUsuário: %s\nDepartamento: %s\nTipo: %s\nAtivo: %s\n",
            idFuncionario, getNome(), getContacto(), getResidencia(), usuario, departamento, tipo, (ativo ? "Sim" : "Não")
        );
    }
}

