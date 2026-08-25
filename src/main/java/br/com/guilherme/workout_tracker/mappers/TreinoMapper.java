package br.com.guilherme.workout_tracker.mappers;
import br.com.guilherme.workout_tracker.dto.ExercicioDTO;
import br.com.guilherme.workout_tracker.dto.TreinoDTO;
import br.com.guilherme.workout_tracker.entities.TreinoModel;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TreinoMapper {

    private final ExercicioMapper exercicioMapper;

    public TreinoMapper(ExercicioMapper exercicioMapper) {
        this.exercicioMapper = exercicioMapper;
    }

    public TreinoModel toModel(TreinoDTO treinoDTO) {
        TreinoModel treinoModel = new TreinoModel();

        treinoModel.setId(treinoDTO.getId());
        treinoModel.setNome(treinoDTO.getNome());
        treinoModel.setDiaDaSemana(treinoDTO.getDiaDaSemana());
        treinoDTO.getExercicios()
                .stream()
                .map(exercicioMapper::toModel)
                .forEach(treinoModel::adicionarExercicio);
        return treinoModel;
    }

    public TreinoDTO toDto(TreinoModel treinoModel) {
        TreinoDTO treinoDTO = new TreinoDTO();

        treinoDTO.setId(treinoModel.getId());
        treinoDTO.setNome(treinoModel.getNome());
        treinoDTO.setDiaDaSemana(treinoModel.getDiaDaSemana());
        List<ExercicioDTO> exercicios = treinoModel.getExercicios()
                .stream()
                .map(exercicioMapper::toDto)
                .toList();
        treinoDTO.setExercicios(exercicios);

        return treinoDTO;
    }
}
