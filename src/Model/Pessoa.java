package Model;

public abstract class Pessoa {
    private int IdPessoa;
    protected String nome;       // Nome da pessoa
    protected String documento;  // Documento da pessoa
    protected String telefone;    // Telefone da pessoa
    protected String email;      // Email da pessoa

    // Construtor
    public Pessoa(String nome, String documento, String telefone, String email) {
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    
    public int getIdPessoa() {
        return IdPessoa; 
    }

    public void setIdPessoa(int IdPessoa) {
        this.IdPessoa = IdPessoa;
    }
    

    public String getNome() {
        return nome; // Retorna o nome da pessoa
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome da pessoa
    }

    public String getDocumento() {
        return documento; // Retorna o documento da pessoa
    }

    public void setDocumento(String documento) {
        this.documento = documento; // Define o documento da pessoa
    }

    public String getTelefone() {
        return telefone; // Retorna o telefone da pessoa
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone; // Define o telefone da pessoa
    }

    public String getEmail() {
        return email; // Retorna o email da pessoa
    }

    public void setEmail(String email) {
        this.email = email; // Define o email da pessoa
    }

    // Método para exibir detalhes (pode ser sobrescrito nas subclasses)
    public String exibirDetalhes() {
        return String.format("Nome: %s\nDocumento: %s\nTelefone: %s\nEmail: %s\n", 
                             nome, documento, telefone, email);
    }
}
