package br.com.guilherme.workout_tracker.repository;

import br.com.guilherme.workout_tracker.entities.TreinoModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TreinoRepository extends JpaRepository<TreinoModel, Long> {}