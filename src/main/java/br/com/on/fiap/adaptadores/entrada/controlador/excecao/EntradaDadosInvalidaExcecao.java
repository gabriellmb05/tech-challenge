package br.com.on.fiap.adaptadores.entrada.controlador.excecao;

public class EntradaDadosInvalidaExcecao extends RuntimeException{

    public EntradaDadosInvalidaExcecao(String message) {
        super(message);
    }
}
