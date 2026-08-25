package br.com.guilherme.workout_tracker.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_treino")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TreinoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private DayOfWeek diaDaSemana;

    @OneToMany(mappedBy = "treino",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ExercicioModel> exercicios = new ArrayList<>();

    public void adicionarExercicio(ExercicioModel exercicio) {
        exercicios.add(exercicio);
        exercicio.setTreino(this);
    }

    public void removerExercicio(ExercicioModel exercicio){
        exercicios.remove(exercicio);
        exercicio.setTreino(null);
    }
}

