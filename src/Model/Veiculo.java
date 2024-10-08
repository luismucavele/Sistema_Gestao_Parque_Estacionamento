package Model;

public class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private String cor;
    private String tipoVeiculo;
    private Cliente proprietario; // Um veículo pertence a um único cliente

    // Construtor
    public Veiculo(String placa, String modelo, String marca, int ano, String cor, String tipoVeiculo, Cliente proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.cor = cor;
        this.tipoVeiculo = tipoVeiculo;
        this.proprietario = proprietario;
    }

    // Getters
    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    // Setters
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    // Método para exibir detalhes do veículo
    public String exibirDetalhes() {
        return String.format(
            "Placa: %s\nModelo: %s\nMarca: %s\nAno: %d\nCor: %s\nTipo de Veículo: %s\nProprietário: %s\n",
            placa, modelo, marca, ano, cor, tipoVeiculo, proprietario.getNome()
        );
    }
}
