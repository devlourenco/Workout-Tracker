package br.com.guilherme.workout_tracker.repository;

import br.com.guilherme.workout_tracker.entities.SerieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<SerieModel, Long> {}

