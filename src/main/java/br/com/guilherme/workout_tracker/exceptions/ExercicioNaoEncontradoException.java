package br.com.guilherme.workout_tracker.exceptions;

public class ExercicioNaoEncontradoException extends RuntimeException {

    public ExercicioNaoEncontradoException() {
        super("Exercício não encontrado");
    }

}
