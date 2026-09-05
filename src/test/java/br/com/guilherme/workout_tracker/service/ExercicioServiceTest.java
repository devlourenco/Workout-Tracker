package br.com.guilherme.workout_tracker.service;

import br.com.guilherme.workout_tracker.dto.ExercicioDTO;
import br.com.guilherme.workout_tracker.entities.ExercicioModel;
import br.com.guilherme.workout_tracker.enums.GrupoMuscularEnum;
import br.com.guilherme.workout_tracker.exceptions.ExercicioNaoEncontradoException;
import br.com.guilherme.workout_tracker.mappers.ExercicioMapper;
import br.com.guilherme.workout_tracker.repository.ExercicioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExercicioServiceTest {

    @Mock
    private ExercicioRepository repository;

    @Mock
    private ExercicioMapper mapper;

    @InjectMocks
    private ExercicioService service;


    @Test
    void deveListarExercicioPorId() {

        Long id = 1L;

        ExercicioModel exercicioModel = new ExercicioModel();
        exercicioModel.setId(id);
        exercicioModel.setNome("Supino");
        exercicioModel.setGrupoMuscular(GrupoMuscularEnum.PEITO);

        ExercicioDTO exercicioDTO = new ExercicioDTO(
                id,
                "Supino",
                GrupoMuscularEnum.PEITO,
                List.of()
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(exercicioModel));

        when(mapper.toDto(exercicioModel))
                .thenReturn(exercicioDTO);


        ExercicioDTO resultado = service.listarPorId(id);


        assertSame(exercicioDTO, resultado);

        verify(repository).findById(id);
        verify(mapper).toDto(exercicioModel);
    }


    @Test
    void deveLancarExcecaoQuandoExercicioNaoForEncontrado() {

        Long id = 99L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                ExercicioNaoEncontradoException.class,
                () -> service.listarPorId(id)
        );


        verify(repository).findById(id);
        verifyNoInteractions(mapper);
    }


    @Test
    void deveAtualizarNomeDoExercicio() {

        Long id = 1L;

        ExercicioModel exercicio = new ExercicioModel();
        exercicio.setId(id);
        exercicio.setNome("Supino");
        exercicio.setGrupoMuscular(GrupoMuscularEnum.PEITO);

        ExercicioDTO exercicioDTO = new ExercicioDTO(
                id,
                "Supino Inclinado",
                GrupoMuscularEnum.PEITO,
                List.of()
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(exercicio));

        when(repository.save(exercicio))
                .thenReturn(exercicio);

        when(mapper.toDto(exercicio))
                .thenReturn(exercicioDTO);


        ExercicioDTO resultado =
                service.atualizarNome(id, "Supino Inclinado");


        assertEquals("Supino Inclinado", exercicio.getNome());
        assertSame(exercicioDTO, resultado);

        verify(repository).findById(id);
        verify(repository).save(exercicio);
        verify(mapper).toDto(exercicio);
    }


    @Test
    void deveAtualizarGrupoMuscular() {

        Long id = 1L;

        ExercicioModel exercicio = new ExercicioModel();
        exercicio.setId(id);
        exercicio.setNome("Remada");
        exercicio.setGrupoMuscular(GrupoMuscularEnum.PEITO);

        ExercicioDTO exercicioDTO = new ExercicioDTO(
                id,
                "Remada",
                GrupoMuscularEnum.COSTAS,
                List.of()
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(exercicio));

        when(repository.save(exercicio))
                .thenReturn(exercicio);

        when(mapper.toDto(exercicio))
                .thenReturn(exercicioDTO);


        ExercicioDTO resultado =
                service.atualizarGrupoMuscular(
                        id,
                        GrupoMuscularEnum.COSTAS
                );


        assertEquals(
                GrupoMuscularEnum.COSTAS,
                exercicio.getGrupoMuscular()
        );

        assertSame(exercicioDTO, resultado);

        verify(repository).findById(id);
        verify(repository).save(exercicio);
        verify(mapper).toDto(exercicio);
    }


    @Test
    void deveLancarExcecaoAoAtualizarExercicioInexistente() {

        Long id = 99L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                ExercicioNaoEncontradoException.class,
                () -> service.atualizarNome(id, "Novo nome")
        );


        verify(repository).findById(id);
        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
    }
}