package Model;

public class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private String cor;
    private String tipoVeiculo; // Exemplo: "Carro", "Moto", etc.
    private boolean estacionado;

    // Construtor completo
    public Veiculo(String placa, String modelo, String marca, String cor, String tipoVeiculo) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.tipoVeiculo = tipoVeiculo;
        this.estacionado = false; // Valor padrão
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

    public boolean isEstacionado() {
        return estacionado;
    }

    public void setEstacionado(boolean estacionado) {
        this.estacionado = estacionado;
    }

    // Método para estacionar o veículo
    public void estacionar() {
        this.estacionado = true;
    }

    // Método para retirar o veículo do estacionamento
    public void retirar() {
        this.estacionado = false;
    }

    // Método para verificar se o veículo é antigo (exemplo: mais de 20 anos)


    // Método para mostrar informações completas do veículo
    public String exibirInformacoes() {
        return "Placa: " + placa + "\n" +
               "Modelo: " + modelo + "\n" +
               "Marca: " + marca + "\n" +
               "Cor: " + cor + "\n" +
               "Tipo: " + tipoVeiculo + "\n" +
               "Estacionado: " + (estacionado ? "Sim" : "Não");
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", cor='" + cor + '\'' +
                ", tipoVeiculo='" + tipoVeiculo + 
                ", estacionado=" + estacionado +
                '}';
    }

    // Método para comparar veículos pela placa (poderia ser usado em uma lista de veículos)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Veiculo veiculo = (Veiculo) obj;
        return placa != null && placa.equals(veiculo.placa);
    }

    @Override
    public int hashCode() {
        return placa != null ? placa.hashCode() : 0;
    }
}
