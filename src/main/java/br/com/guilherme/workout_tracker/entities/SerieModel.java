package br.com.guilherme.workout_tracker.entities;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_series")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SerieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer reps;
    private Integer rir;
    private BigDecimal carga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_id")
    private ExercicioModel exercicio;
}