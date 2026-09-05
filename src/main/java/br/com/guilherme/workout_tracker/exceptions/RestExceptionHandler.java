package br.com.guilherme.workout_tracker.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        Map<String, String> erros = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(erro ->
                        erros.put(erro.getField(), erro.getDefaultMessage())
                );

        return ResponseEntity.badRequest().body(erros);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(
            ConstraintViolationException exception) {

        Map<String, String> erros = new HashMap<>();

        exception.getConstraintViolations().forEach(violacao ->
                erros.put(
                        violacao.getPropertyPath().toString(),
                        violacao.getMessage()
                )
        );

        return ResponseEntity.badRequest().body(erros);
    }

}
