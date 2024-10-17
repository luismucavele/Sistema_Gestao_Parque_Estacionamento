package Model;

public class Espaco {

    private String identificacao; // Identificação do espaço (ex: A1, B2)
    private boolean ocupado; // Se o espaço está ocupado ou não

    // Construtor
    public Espaco(String identificacao) {
        this.identificacao = identificacao;
        this.ocupado = false; // Inicializa como desocupado
    }

    // Método para ocupar o espaço
    public void ocupar() {
        this.ocupado = true;
    }

    // Método para liberar o espaço
    public void liberar() {
        this.ocupado = false;
    }

    // Getters e Setters
    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    // Método para exibir os detalhes do espaço
    public String getDetalhes() {
        return String.format("Espaço: %s - %s", identificacao, ocupado ? "Ocupado" : "Disponível");
    }
}
