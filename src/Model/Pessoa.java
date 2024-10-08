/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Pessoa {
    private String nome;
    private String contacto;
    private String residencia;

    // Construtor
    public Pessoa(String nome, String contacto, String residencia) {
        this.nome = nome;
        this.contacto = contacto;
        this.residencia = residencia;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    // Método para exibir detalhes (será sobrescrito nas subclasses)
    public String exibirDetalhes() {
        return String.format("Nome: %s\nContacto: %s\nResidência: %s\n", nome, contacto, residencia);
    }
}
