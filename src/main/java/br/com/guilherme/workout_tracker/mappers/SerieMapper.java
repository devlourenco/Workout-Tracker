package br.com.guilherme.workout_tracker.mappers;

import br.com.guilherme.workout_tracker.dto.SerieDTO;
import br.com.guilherme.workout_tracker.entities.SerieModel;
import org.springframework.stereotype.Component;

@Component
public class SerieMapper {

    public SerieModel toModel(SerieDTO serieDTO) {
        SerieModel serieModel = new SerieModel();
        serieModel.setId(serieDTO.getId());
        serieModel.setReps(serieDTO.getReps());
        serieModel.setRir(serieDTO.getRir());
        serieModel.setCarga(serieDTO.getCarga());

        return serieModel;
    }

    public SerieDTO toDto(SerieModel serieModel) {
        SerieDTO serieDTO = new SerieDTO();
        serieDTO.setId(serieModel.getId());
        serieDTO.setReps(serieModel.getReps());
        serieDTO.setRir(serieModel.getRir());
        serieDTO.setCarga(serieModel.getCarga());

        return serieDTO;
    }
}
