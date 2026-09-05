package br.com.guilherme.workout_tracker.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TreinoDTO {

    private Long id;

    @NotBlank(message = "O nome do treino é obrigatório")
    private String nome;

    @NotNull(message = "O dia da semana é obrigatório")
    private DayOfWeek diaDaSemana;

    @NotEmpty(message = "O treino deve possuir pelo menos um exercício")
    @Valid
    private List<ExercicioDTO> exercicios;

}
