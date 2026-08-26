package br.com.guilherme.workout_tracker.service;

import br.com.guilherme.workout_tracker.dto.ExercicioDTO;
import br.com.guilherme.workout_tracker.entities.ExercicioModel;
import br.com.guilherme.workout_tracker.enums.GrupoMuscularEnum;
import br.com.guilherme.workout_tracker.mappers.ExercicioMapper;
import br.com.guilherme.workout_tracker.repository.ExercicioRepository;
import org.springframework.stereotype.Service;

@Service
public class ExercicioService {

    private final ExercicioRepository repository;
    private final ExercicioMapper mapper;

    public ExercicioService(ExercicioRepository repository, ExercicioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ExercicioDTO listarPorId(Long id) {
        ExercicioModel exercicio = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercício não encontrado."));
        return mapper.toDto(exercicio);
    }

    public ExercicioDTO atualizarNome(Long id, String nome) {
        ExercicioModel exercicio = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercício não encontrado."));
        exercicio.setNome(nome);
        ExercicioModel exercicioSalvo = repository.save(exercicio);

        return mapper.toDto(exercicioSalvo);
    }

    public ExercicioDTO atualizarGrupoMuscular(Long id, GrupoMuscularEnum grupoMuscular){
        ExercicioModel exercicio = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercício não encontrado."));
        exercicio.setGrupoMuscular(grupoMuscular);
        ExercicioModel exercicioSalvo = repository.save(exercicio);

        return mapper.toDto(exercicioSalvo);
    }
}
