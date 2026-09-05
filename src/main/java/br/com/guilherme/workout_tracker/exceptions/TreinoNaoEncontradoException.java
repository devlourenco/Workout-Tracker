package br.com.guilherme.workout_tracker.exceptions;

public class TreinoNaoEncontradoException extends RuntimeException {

    public TreinoNaoEncontradoException() {
        super("Treino não encontrado");
    }
}
