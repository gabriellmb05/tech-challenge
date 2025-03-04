package br.com.on.fiap.hexagono.util;

public class FormatadorCpf {

    private FormatadorCpf() {}

    public static String formatarCpf(String cpf) {
        return String.format(
                "%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9, 11));
    }
}
