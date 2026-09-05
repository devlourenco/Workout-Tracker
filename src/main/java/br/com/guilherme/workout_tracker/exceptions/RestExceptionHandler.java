package br.com.guilherme.workout_tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TreinoNaoEncontradoException.class)
    public ResponseEntity<String> handlerTreinoNaoEncontrado(TreinoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ExercicioNaoEncontradoException.class)
    public ResponseEntity<String> handlerExercicioNaoEncontrado(ExercicioNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(SerieNaoEncontradaException.class)
    public ResponseEntity<String> handlerSerieNaoEncontrada(SerieNaoEncontradaException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
