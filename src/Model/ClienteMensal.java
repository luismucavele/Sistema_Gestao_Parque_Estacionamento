package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class ClienteMensal extends Cliente {
    private LocalDateTime prazoSaida;

    public ClienteMensal(String nome, String contacto, String residencia, String matricula, String corCarro, double valorPorHora, String tipoPagamento, LocalDateTime prazoSaida) {
        super(nome, contacto, residencia, matricula, corCarro, valorPorHora, tipoPagamento);
        this.prazoSaida = prazoSaida;
    }

    // Método para calcular o valor com multa se o prazo for excedido
    @Override
    public double calcularValorTotal() {
        if (dataHoraSaida == null) {
            dataHoraSaida = LocalDateTime.now();
        }

        Duration duracao = Duration.between(dataHoraEntrada, dataHoraSaida);
        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();

        if (minutos > 0) {
            horas++;
        }

        double valorTotal = horas * valorPorHora;

        // Se o cliente exceder o prazo, aplica multa de 10%
        if (dataHoraSaida.isAfter(prazoSaida)) {
            double multa = valorTotal * 0.10; // 10% de multa
            valorTotal += multa;
        }

        return valorTotal;
    }

    public LocalDateTime getPrazoSaida() {
        return prazoSaida;
    }

    public void setPrazoSaida(LocalDateTime prazoSaida) {
        this.prazoSaida = prazoSaida;
    }

    @Override
    public String exibirDetalhes() {
        return String.format(
            "ID Cliente: %d\nNome: %s\nContacto: %s\nResidência: %s\nMatrícula: %s\nCor do Carro: %s\nValor por Hora: %.2f\nTipo de Pagamento: %s\nEstacionado: %s\nHora de Entrada: %s\nPrazo de Saída: %s\n",
            idCliente, getNome(), getContacto(), getResidencia(), matricula, corCarro, valorPorHora, tipoPagamento, (estacionado ? "Sim" : "Não"), dataHoraEntrada.toString(), prazoSaida.toString()
        );
    }
}
