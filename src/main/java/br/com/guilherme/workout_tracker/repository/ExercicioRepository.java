package br.com.guilherme.workout_tracker.repository;

import br.com.guilherme.workout_tracker.entities.ExercicioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<ExercicioModel, Long> {
}
