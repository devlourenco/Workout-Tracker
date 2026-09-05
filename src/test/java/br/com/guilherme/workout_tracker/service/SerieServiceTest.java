package br.com.guilherme.workout_tracker.service;

import br.com.guilherme.workout_tracker.dto.SerieDTO;
import br.com.guilherme.workout_tracker.entities.SerieModel;
import br.com.guilherme.workout_tracker.exceptions.SerieNaoEncontradaException;
import br.com.guilherme.workout_tracker.mappers.SerieMapper;
import br.com.guilherme.workout_tracker.repository.SerieRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SerieServiceTest {

    @Mock
    private SerieRepository repository;

    @Mock
    private SerieMapper mapper;

    @InjectMocks
    private SerieService service;


    @Test
    void deveListarSeriePorId() {

        Long id = 1L;

        SerieModel serieModel = new SerieModel();
        serieModel.setId(id);
        serieModel.setReps(10);
        serieModel.setRir(2);
        serieModel.setCarga(new BigDecimal("50"));

        SerieDTO serieDTO = new SerieDTO(
                id,
                10,
                2,
                new BigDecimal("50")
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(serieModel));

        when(mapper.toDto(serieModel))
                .thenReturn(serieDTO);


        SerieDTO resultado = service.listarPorId(id);


        assertSame(serieDTO, resultado);

        verify(repository).findById(id);
        verify(mapper).toDto(serieModel);
    }


    @Test
    void deveLancarExcecaoQuandoSerieNaoForEncontrada() {

        Long id = 99L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                SerieNaoEncontradaException.class,
                () -> service.listarPorId(id)
        );


        verify(repository).findById(id);
        verifyNoInteractions(mapper);
    }


    @Test
    void deveAtualizarRir() {

        Long id = 1L;

        SerieModel serie = new SerieModel();
        serie.setId(id);
        serie.setReps(10);
        serie.setRir(3);
        serie.setCarga(new BigDecimal("50"));

        SerieDTO serieDTO = new SerieDTO(
                id,
                10,
                1,
                new BigDecimal("50")
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(serie));

        when(repository.save(serie))
                .thenReturn(serie);

        when(mapper.toDto(serie))
                .thenReturn(serieDTO);


        SerieDTO resultado = service.atualizarRir(id, 1);


        assertEquals(1, serie.getRir());
        assertSame(serieDTO, resultado);

        verify(repository).findById(id);
        verify(repository).save(serie);
        verify(mapper).toDto(serie);
    }


    @Test
    void deveAtualizarReps() {

        Long id = 1L;

        SerieModel serie = new SerieModel();
        serie.setId(id);
        serie.setReps(8);
        serie.setRir(2);
        serie.setCarga(new BigDecimal("50"));

        SerieDTO serieDTO = new SerieDTO(
                id,
                12,
                2,
                new BigDecimal("50")
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(serie));

        when(repository.save(serie))
                .thenReturn(serie);

        when(mapper.toDto(serie))
                .thenReturn(serieDTO);


        SerieDTO resultado = service.atualizarReps(id, 12);


        assertEquals(12, serie.getReps());
        assertSame(serieDTO, resultado);

        verify(repository).findById(id);
        verify(repository).save(serie);
        verify(mapper).toDto(serie);
    }


    @Test
    void deveAtualizarCarga() {

        Long id = 1L;

        SerieModel serie = new SerieModel();
        serie.setId(id);
        serie.setReps(10);
        serie.setRir(2);
        serie.setCarga(new BigDecimal("50"));

        BigDecimal novaCarga = new BigDecimal("55");

        SerieDTO serieDTO = new SerieDTO(
                id,
                10,
                2,
                novaCarga
        );

        when(repository.findById(id))
                .thenReturn(Optional.of(serie));

        when(repository.save(serie))
                .thenReturn(serie);

        when(mapper.toDto(serie))
                .thenReturn(serieDTO);


        SerieDTO resultado =
                service.atualizarCarga(id, novaCarga);


        assertEquals(novaCarga, serie.getCarga());
        assertSame(serieDTO, resultado);

        verify(repository).findById(id);
        verify(repository).save(serie);
        verify(mapper).toDto(serie);
    }


    @Test
    void deveLancarExcecaoAoAtualizarSerieInexistente() {

        Long id = 99L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                SerieNaoEncontradaException.class,
                () -> service.atualizarRir(id, 2)
        );


        verify(repository).findById(id);
        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
    }
}