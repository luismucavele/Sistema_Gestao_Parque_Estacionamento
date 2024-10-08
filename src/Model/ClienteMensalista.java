package Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteMensalista extends Cliente {
    private LocalDate prazoSaida; // Data máxima de saída do cliente mensalista

    // Construtor que inicializa os atributos principais do cliente mensalista
    public ClienteMensalista(String nome, String documento, String telefone, String email, LocalDate prazoSaida) {
        super(nome, documento, telefone, email); // Chama o construtor da superclasse
        this.prazoSaida = prazoSaida; // Inicializa o prazo de saída
    }

    // Método para calcular desconto para cliente mensalista
    @Override
    public double calcularDesconto(double valor) {
        // Exemplo: 10% de desconto para clientes mensalistas
        return valor * 0.10;
    }

    // Método para calcular o valor total a ser pago pelo cliente
    public double calcularValorTotal() {
        LocalDateTime dataHoraSaida = LocalDateTime.now(); // Define a hora de saída como agora
        Duration duracao = Duration.between(dataHoraEntrada, dataHoraSaida);
        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();

        if (minutos > 0) {
            horas++; // Arredonda para cima se houver minutos
        }

        double valorTotal = horas * valorPorHora; // Cálculo do valor total

        // Se o cliente exceder o prazo, aplica multa de 10%
        if (dataHoraSaida.toLocalDate().isAfter(prazoSaida)) {
            double multa = valorTotal * 0.10; // 10% de multa
            valorTotal += multa;
        }

        return valorTotal; // Retorna o valor total a ser pago
    }

    // Getters e Setters
    public LocalDate getPrazoSaida() {
        return prazoSaida; // Retorna o prazo de saída
    }

    public void setPrazoSaida(LocalDate prazoSaida) {
        this.prazoSaida = prazoSaida; // Define o prazo de saída
    }

    // Método para exibir detalhes do cliente mensalista
    @Override
    public String exibirDetalhes() {
        return String.format(
            "ID Cliente: %d\nNome: %s\nDocumento: %s\nTelefone: %s\nEmail: %s\nPrazo de Saída: %s\nValor Total: %.2f\n",
            idCliente, getNome(), getDocumento(), getTelefone(), getEmail(), prazoSaida.toString(), calcularValorTotal()
        );
    }
}
