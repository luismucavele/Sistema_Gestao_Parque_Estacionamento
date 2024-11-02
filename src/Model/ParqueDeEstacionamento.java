
package Model;

public class ParqueDeEstacionamento {
    private int idParque;
    private String nome;
    private String endereco;
    private int capacidadeTotal;
    private int vagasDisponiveis;

    // Construtor padrão
    public ParqueDeEstacionamento() {}

    // Construtor com parâmetros
    public ParqueDeEstacionamento(int idParque, String nome, String endereco, int capacidadeTotal, int vagasDisponiveis) {
        this.idParque = idParque;
        this.nome = nome;
        this.endereco = endereco;
        this.capacidadeTotal = capacidadeTotal;
        this.vagasDisponiveis = vagasDisponiveis;
    }

    // Getters e Setters
    public int getIdParque() {
        return idParque;
    }

    public void setIdParque(int idParque) {
        this.idParque = idParque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(int capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }
}
