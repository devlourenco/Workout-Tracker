package br.com.guilherme.workout_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SerieDTO {

    private Long id;
    private Integer reps;
    private Integer rir;
    private BigDecimal carga;
}
