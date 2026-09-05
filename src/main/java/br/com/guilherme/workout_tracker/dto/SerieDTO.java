package br.com.guilherme.workout_tracker.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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


    @NotNull(message = "O número de repetições é obrigatório")
    @Min(value = 1, message = "O número de repetições deve ser maior que zero")
    private Integer reps;

    @NotNull(message = "O RIR é obrigatório")
    @Min(value = 0, message = "O RIR não pode ser negativo")
    private Integer rir;

    @NotNull(message = "A carga é obrigatória")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "A carga não pode ser negativa")
    private BigDecimal carga;
}
