package br.com.guilherme.workout_tracker.mappers;

import br.com.guilherme.workout_tracker.dto.ExercicioDTO;
import br.com.guilherme.workout_tracker.dto.SerieDTO;
import br.com.guilherme.workout_tracker.entities.ExercicioModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExercicioMapper {
    private final SerieMapper serieMapper;

    public ExercicioMapper(SerieMapper serieMapper) {
        this.serieMapper = serieMapper;
    }

    public ExercicioModel toModel(ExercicioDTO exercicioDTO){
        ExercicioModel exercicioModel = new ExercicioModel();

        exercicioModel.setId(exercicioDTO.getId());
        exercicioModel.setNome(exercicioDTO.getNome());
        exercicioModel.setGrupoMuscular(exercicioDTO.getGrupoMuscular());
        exercicioDTO.getSeries()
                .stream()
                .map(serieMapper::toModel)
                .forEach(exercicioModel::adicionarSerie);

        return exercicioModel;
    }

    public ExercicioDTO toDto(ExercicioModel exercicioModel){
        ExercicioDTO exercicioDTO = new ExercicioDTO();

        exercicioDTO.setId(exercicioModel.getId());
        exercicioDTO.setNome(exercicioModel.getNome());
        exercicioDTO.setGrupoMuscular(exercicioModel.getGrupoMuscular());
        List<SerieDTO> seriesDTO =  exercicioModel.getSeries()
                .stream()
                .map(serieMapper::toDto)
                .toList();
        exercicioDTO.setSeries(seriesDTO);

        return exercicioDTO;
    }
}
