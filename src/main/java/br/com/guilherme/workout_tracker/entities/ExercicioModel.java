package br.com.guilherme.workout_tracker.entities;

import jakarta.persistence.*;
import br.com.guilherme.workout_tracker.enums.GrupoMuscularEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_exercicios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExercicioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private GrupoMuscularEnum grupoMuscular;

    @OneToMany(mappedBy = "exercicio",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SerieModel> series = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id")
    private TreinoModel treino;

    public void adicionarSerie(SerieModel serie) {
        series.add(serie);
        serie.setExercicio(this);
    }

    public void removerSerie(SerieModel serie) {
        series.remove(serie);
        serie.setExercicio(null);
    }
}
