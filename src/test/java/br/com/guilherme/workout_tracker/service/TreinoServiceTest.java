package br.com.guilherme.workout_tracker.service;

import br.com.guilherme.workout_tracker.dto.TreinoDTO;
import br.com.guilherme.workout_tracker.entities.TreinoModel;
import br.com.guilherme.workout_tracker.exceptions.TreinoNaoEncontradoException;
import br.com.guilherme.workout_tracker.mappers.TreinoMapper;
import br.com.guilherme.workout_tracker.repository.TreinoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreinoServiceTest {

    @Mock
    private TreinoRepository repository;

    @Mock
    private TreinoMapper mapper;

    @InjectMocks
    private TreinoService service;


    @Test
    void deveCadastrarTreino() {

        TreinoDTO treinoDTO = new TreinoDTO(
                null,
                "Treino A",
                DayOfWeek.MONDAY,
                List.of()
        );

        TreinoModel treinoModel = new TreinoModel();
        treinoModel.setNome("Treino A");
        treinoModel.setDiaDaSemana(DayOfWeek.MONDAY);

        TreinoModel treinoSalvo = new TreinoModel();
        treinoSalvo.setId(1L);
        treinoSalvo.setNome("Treino A");
        treinoSalvo.setDiaDaSemana(DayOfWeek.MONDAY);

        TreinoDTO treinoDTOResultado = new TreinoDTO(
                1L,
                "Treino A",
                DayOfWeek.MONDAY,
                List.of()
        );

        when(mapper.toModel(treinoDTO))
                .thenReturn(treinoModel);

        when(repository.save(treinoModel))
                .thenReturn(treinoSalvo);

        when(mapper.toDto(treinoSalvo))
                .thenReturn(treinoDTOResultado);


        TreinoDTO resultado = service.cadastrarTreino(treinoDTO);


        assertSame(treinoDTOResultado, resultado);

        verify(mapper).toModel(treinoDTO);
        verify(repository).save(treinoModel);
        verify(mapper).toDto(treinoSalvo);
    }


    @Test
    void deveListarTodosOsTreinos() {

        TreinoModel treino1 = new TreinoModel();
        treino1.setId(1L);
        treino1.setNome("Treino A");
        treino1.setDiaDaSemana(DayOfWeek.MONDAY);

        TreinoModel treino2 = new TreinoModel();
        treino2.setId(2L);
        treino2.setNome("Treino B");
        treino2.setDiaDaSemana(DayOfWeek.TUESDAY);

        TreinoDTO dto1 = new TreinoDTO(
                1L,
                "Treino A",
                DayOfWeek.MONDAY,
                List.of()
        );

        TreinoDTO dto2 = new TreinoDTO(
                2L,
                "Treino B",
                DayOfWeek.TUESDAY,
                List.of()
        );

        when(repository.findAll())
                .thenReturn(List.of(treino1, treino2));

        when(mapper.toDto(treino1))
                .thenReturn(dto1);

        when(mapper.toDto(treino2))
                .thenReturn(dto2);


        List<TreinoDTO> resultado = service.listarTreinos();


        assertEquals(2, resultado.size());

        assertSame(dto1, resultado.get(0));
        assertSame(dto2, resultado.get(1));

        verify(repository).findAll();

        verify(mapper).toDto(treino1);
        verify(mapper).toDto(treino2);
    }


    @Test
    void deveListarTreinoPorId() {

        Long id = 1L;

        TreinoModel treinoModel = new TreinoModel();
        treinoModel.setId(id);
        treinoModel.setNome("Treino A");
        treinoModel.setDiaDaSemana(DayOfWeek.MONDAY);

        TreinoDTO treinoDTO = new TreinoDTO(
                id,
                "Treino A",
                DayOfWeek.MONDAY,
                List.of()
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(treinoModel));

        when(mapper.toDto(treinoModel))
                .thenReturn(treinoDTO);


        TreinoDTO resultado = service.listarPorId(id);


        assertSame(treinoDTO, resultado);

        verify(repository).findById(id);
        verify(mapper).toDto(treinoModel);
    }


    @Test
    void deveLancarExcecaoQuandoTreinoNaoForEncontrado() {

        Long id = 1L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                TreinoNaoEncontradoException.class,
                () -> service.listarPorId(id)
        );


        verify(repository).findById(id);

        verifyNoInteractions(mapper);
    }


    @Test
    void deveAtualizarTreino() {

        Long id = 1L;

        TreinoModel treinoExistente = new TreinoModel();
        treinoExistente.setId(id);
        treinoExistente.setNome("Treino Antigo");
        treinoExistente.setDiaDaSemana(DayOfWeek.MONDAY);

        TreinoDTO atualizacao = new TreinoDTO(
                null,
                "Treino Atualizado",
                DayOfWeek.WEDNESDAY,
                null
        );

        TreinoDTO treinoAtualizadoDTO = new TreinoDTO(
                id,
                "Treino Atualizado",
                DayOfWeek.WEDNESDAY,
                List.of()
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(treinoExistente));

        when(repository.save(treinoExistente))
                .thenReturn(treinoExistente);

        when(mapper.toDto(treinoExistente))
                .thenReturn(treinoAtualizadoDTO);


        TreinoDTO resultado =
                service.atualizarTreino(id, atualizacao);


        assertSame(treinoAtualizadoDTO, resultado);

        assertEquals(
                "Treino Atualizado",
                treinoExistente.getNome()
        );

        assertEquals(
                DayOfWeek.WEDNESDAY,
                treinoExistente.getDiaDaSemana()
        );

        verify(repository).findById(id);
        verify(repository).save(treinoExistente);
        verify(mapper).toDto(treinoExistente);
    }


    @Test
    void deveLancarExcecaoAoAtualizarTreinoInexistente() {

        Long id = 99L;

        TreinoDTO atualizacao = new TreinoDTO(
                null,
                "Treino Novo",
                null,
                null
        );

        when(repository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                TreinoNaoEncontradoException.class,
                () -> service.atualizarTreino(id, atualizacao)
        );


        verify(repository).findById(id);

        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
    }



    @Test
    void deveDeletarTreino() {

        Long id = 1L;

        TreinoModel treino = new TreinoModel();
        treino.setId(id);
        treino.setNome("Treino A");

        when(repository.findById(id))
                .thenReturn(Optional.of(treino));


        service.deletarTreino(id);


        verify(repository).findById(id);
        verify(repository).delete(treino);
    }


    @Test
    void deveLancarExcecaoAoDeletarTreinoInexistente() {

        Long id = 99L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                TreinoNaoEncontradoException.class,
                () -> service.deletarTreino(id)
        );


        verify(repository).findById(id);

        verify(repository, never())
                .delete(any(TreinoModel.class));
    }
}