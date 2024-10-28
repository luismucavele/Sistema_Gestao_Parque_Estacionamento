package Model;

public class ClienteVaga {
    private int idCliente;
    private String nome;
    private String residencia;
    private String contacto;
    private String matricula;
    private String cor;
    private String tipoPagamento;
    private double valorPorHora;

    public ClienteVaga(int idCliente, String nome, String residencia, String contacto, String matricula, String cor, String tipoPagamento, double valorPorHora) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.residencia = residencia;
        this.contacto = contacto;
        this.matricula = matricula;
        this.cor = cor;
        this.tipoPagamento = tipoPagamento;
        this.valorPorHora = valorPorHora;
    }

    // Getters para cada atributo
    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getResidencia() {
        return residencia;
    }

    public String getContacto() {
        return contacto;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCor() {
        return cor;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }
}
