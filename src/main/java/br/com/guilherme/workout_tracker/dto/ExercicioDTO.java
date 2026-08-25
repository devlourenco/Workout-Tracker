package br.com.guilherme.workout_tracker.dto;

import br.com.guilherme.workout_tracker.enums.GrupoMuscularEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExercicioDTO {

    private Long id;
    private String nome;
    private GrupoMuscularEnum grupoMuscular;
    private List<SerieDTO> series;
}
