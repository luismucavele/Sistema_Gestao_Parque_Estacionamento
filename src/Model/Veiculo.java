package Model;

public class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private String cor;
    private String tipoVeiculo;
    private Cliente proprietario;

    public Veiculo(String placa, String modelo, String marca, int ano, String cor, String tipoVeiculo, Cliente proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.cor = cor;
        this.tipoVeiculo = tipoVeiculo;
        this.proprietario = proprietario;
    }

    // Getters e Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    // Métodos adicionais
    public void atualizarModelo(String novoModelo) {
        this.modelo = novoModelo;
    }

    public void atualizarCor(String novaCor) {
        this.cor = novaCor;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                ", cor='" + cor + '\'' +
                ", tipoVeiculo='" + tipoVeiculo + '\'' +
                ", proprietario=" + proprietario +
                '}';
    }
}
