package br.com.guilherme.workout_tracker.dto;
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
    private String nome;
    private DayOfWeek diaDaSemana;
    private List<ExercicioDTO> exercicios;

}
