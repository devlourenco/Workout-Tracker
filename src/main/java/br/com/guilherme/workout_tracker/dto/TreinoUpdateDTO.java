package br.com.guilherme.workout_tracker.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoUpdateDTO {

    @Pattern(
            regexp = ".*\\S.*",
            message = "O nome do treino não pode ser vazio"
    )
    private String nome;

    private DayOfWeek diaDaSemana;
}