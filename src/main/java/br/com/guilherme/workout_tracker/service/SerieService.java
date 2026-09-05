package br.com.guilherme.workout_tracker.service;

import br.com.guilherme.workout_tracker.dto.SerieDTO;
import br.com.guilherme.workout_tracker.entities.SerieModel;
import br.com.guilherme.workout_tracker.exceptions.SerieNaoEncontradaException;
import br.com.guilherme.workout_tracker.mappers.SerieMapper;
import br.com.guilherme.workout_tracker.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SerieService {

    private final SerieRepository repository;
    private final SerieMapper mapper;

    public SerieService(SerieRepository repository, SerieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SerieDTO listarPorId(Long id){
        SerieModel serie = repository.findById(id).orElseThrow(()-> new SerieNaoEncontradaException());
        return (mapper.toDto(serie));
    }
    public SerieDTO atualizarRir(Long id, Integer rir){
        SerieModel serie = repository.findById(id).orElseThrow(()-> new SerieNaoEncontradaException());

        serie.setRir(rir);
        SerieModel serieSalva = repository.save(serie);

        return mapper.toDto(serieSalva);
    }

    public SerieDTO atualizarReps(Long id, Integer reps){
        SerieModel serie = repository.findById(id).orElseThrow(() -> new SerieNaoEncontradaException());

        serie.setReps(reps);
        SerieModel serieSalva = repository.save(serie);

        return mapper.toDto(serieSalva);
    }

    public SerieDTO atualizarCarga(Long id, BigDecimal carga){
        SerieModel serie = repository.findById(id).orElseThrow(()-> new SerieNaoEncontradaException());

        serie.setCarga(carga);
        SerieModel serieSalva = repository.save(serie);
        return mapper.toDto(serieSalva);
    }
}
