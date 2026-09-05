package br.com.guilherme.workout_tracker.exceptions;

public class SerieNaoEncontradaException extends RuntimeException {

    public SerieNaoEncontradaException() {
        super("Série não encontrada");
    }
}
