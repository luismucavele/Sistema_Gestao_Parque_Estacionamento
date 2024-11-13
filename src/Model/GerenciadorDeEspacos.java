package Model;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeEspacos {

    private List<Vaga> espacos;
    private int totalEspacos;
    private int totalVip;
    private int totalReservadoDeficientes;

    public GerenciadorDeEspacos(int totalEspacos, int totalVip, int totalReservadoDeficientes) {
        this.totalEspacos = totalEspacos;
        this.totalVip = totalVip;
        this.totalReservadoDeficientes = totalReservadoDeficientes;
        this.espacos = new ArrayList<>();

        // Criar os espaços de estacionamento normais
        for (int i = 0; i < totalEspacos; i++) {
            vaga.add(new Vaga("Espaco-" + (i + 1), 10.0, false));
        }

        // Criar espaços VIP
        for (int i = 0; i < totalVip; i++) {
            espacos.add(new Vaga("VIP-" + (i + 1), 20.0, true));
        }

        // Criar espaços reservados para deficientes
        for (int i = 0; i < totalReservadoDeficientes; i++) {
            espacos.add(new Vaga("Deficiente-" + (i + 1), 10.0, false));
        }
    }

    public void definirValorPorHora(String identificador, double novoValor) {
        Vaga espaco = procurarPorIdentificador(identificador);
        if (espaco != null) {
            espaco.setValorPorHora(novoValor);
            System.out.println("Valor por hora atualizado para o espaço " + identificador + ": " + novoValor);
        } else {
            System.out.println("Espaço não encontrado!");
        }
    }

    public int getTotalEspacos() {
        return totalEspacos;
    }

    public int getTotalEspacosLivres() {
        int count = 0;
        for (Vaga espaco : espacos) {
            if (!espaco.isOcupado()) {
                count++;
            }
        }
        return count;
    }

    public int getTotalEspacosOcupados() {
        return totalEspacos - getTotalEspacosLivres();
    }

    public List<Vaga> getEspacos() {
        return espacos;
    }

    public void cadastrarEspaco(String identificador, double valorPorHora, boolean isVip,boolean ocupado) {
        Vaga novoEspaco = new Vaga (identificador, valorPorHora, isVip,ocupado);
        espacos.add(novoEspaco);
        totalEspacos++;
    }

    public void removerEspaco(Vaga espaco) {
        espacos.remove(espaco);
        totalEspacos--;
    }

    public Vaga procurarPorIdentificador(String identificador) {
        for (Vaga espaco : espacos) {
            if (espaco.getIdentificador().equalsIgnoreCase(identificador)) {
                return espaco;
            }
        }
        return null;
    }
}
