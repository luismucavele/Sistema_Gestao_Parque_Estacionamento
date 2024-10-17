
package Model;

import java.util.ArrayList;

public class ParqueDeEstacionamento {
    private int idParque;
    private String nome;
    private String localizacao;
    private int capacidadeTotal;
    private int vagasDisponiveis;
    private ArrayList<Vaga> vagas;

    public ParqueDeEstacionamento(int idParque, String nome, String localizacao, int capacidadeTotal, int vagasDisponiveis) {
        this.idParque = idParque;
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidadeTotal = capacidadeTotal;
        this.vagasDisponiveis = vagasDisponiveis;
        this.vagas = new ArrayList<>();
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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

    public ArrayList<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(ArrayList<Vaga> vagas) {
        this.vagas = vagas;
    }

    // Métodos adicionais
    public void adicionarVaga(Vaga vaga) {
        this.vagas.add(vaga);
        this.vagasDisponiveis--;
    }

    public void removerVaga(Vaga vaga) {
        this.vagas.remove(vaga);
        this.vagasDisponiveis++;
    }

    @Override
    public String toString() {
        return "ParqueDeEstacionamento{" +
                "idParque=" + idParque +
                ", nome='" + nome + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", capacidadeTotal=" + capacidadeTotal +
                ", vagasDisponiveis=" + vagasDisponiveis +
                '}';
    }
}
