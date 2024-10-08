/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

public class Validacao {

    // Método para verificar se o campo está vazio
    public static boolean isCampoVazio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    // Método para verificar se a senha tem o tamanho mínimo
    public static boolean isTamanhoSenhaValido(String senha, int tamanhoMinimo) {
        return senha.length() >= tamanhoMinimo;
    }
}
