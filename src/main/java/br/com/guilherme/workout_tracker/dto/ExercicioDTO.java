package br.com.guilherme.workout_tracker.dto;

import br.com.guilherme.workout_tracker.enums.GrupoMuscularEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "O nome do exercício é obrigatório")
    private String nome;

    @NotNull(message = "O grupo muscular é obrigatório")
    private GrupoMuscularEnum grupoMuscular;

    @NotEmpty(message = "O treino deve possuir pelo menos uma série")
    @Valid
    private List<SerieDTO> series;
}
