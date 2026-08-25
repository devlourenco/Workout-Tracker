package br.com.guilherme.workout_tracker.service;

import br.com.guilherme.workout_tracker.dto.TreinoDTO;
import br.com.guilherme.workout_tracker.entities.TreinoModel;
import br.com.guilherme.workout_tracker.mappers.TreinoMapper;
import br.com.guilherme.workout_tracker.repository.TreinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {
    TreinoRepository repository;
    TreinoMapper mapper;

    public TreinoService(TreinoRepository repository, TreinoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TreinoDTO cadastrarTreino(TreinoDTO treinoDTO) {
        TreinoModel novoTreino = mapper.toModel(treinoDTO);
        TreinoModel treinoSalvo = repository.save(novoTreino);

        return (mapper.toDto(treinoSalvo));
    }

    public List<TreinoDTO> listarTreinos() {
        List<TreinoModel> listaDeTreinos = repository.findAll();
        List<TreinoDTO> treinos = listaDeTreinos.stream()
                .map(mapper::toDto)
                .toList();

        return treinos;
    }

    public TreinoDTO listarPorId(Long id) {
        TreinoModel treinoPorId = repository.findById(id).orElseThrow(() -> new RuntimeException("Treino não encontrado"));
        TreinoDTO treino = mapper.toDto(treinoPorId);

        return treino;
    }

    public void deletarTreino(Long id){
        TreinoModel treino = repository.findById(id).orElseThrow(()-> new RuntimeException("Treino não encontrado"));
        repository.delete(treino);
    }

    public TreinoDTO atualizarTreino(Long id, TreinoDTO treinoDTO) {

        TreinoModel treino = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));

        if (treinoDTO.getNome() != null) {
            treino.setNome(treinoDTO.getNome());
        }

        if (treinoDTO.getDiaDaSemana() != null) {
            treino.setDiaDaSemana(treinoDTO.getDiaDaSemana());
        }

        TreinoModel treinoAtualizado = repository.save(treino);

        return mapper.toDto(treinoAtualizado);
    }
}
