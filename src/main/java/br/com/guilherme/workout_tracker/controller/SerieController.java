package br.com.guilherme.workout_tracker.controller;

import br.com.guilherme.workout_tracker.dto.SerieDTO;
import br.com.guilherme.workout_tracker.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/series")
public class SerieController {
    private final SerieService service;

    public SerieController(SerieService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> listarPorId(@PathVariable Long id) {
        SerieDTO serie = service.listarPorId(id);
        return ResponseEntity.ok(serie);
    }

    @PatchMapping("/{id}/rir")
    public ResponseEntity<SerieDTO> atualizarRir(@PathVariable Long id, @RequestParam Integer rir) {
        SerieDTO serie = service.atualizarRir(id, rir);
        return ResponseEntity.ok(serie);
    }

    @PatchMapping("/{id}/reps")
    public ResponseEntity<SerieDTO> atualizarReps(@PathVariable Long id, @RequestParam Integer reps) {
        SerieDTO serie = service.atualizarReps(id, reps);
        return ResponseEntity.ok(serie);
    }

    @PatchMapping("/{id}/carga")
    public ResponseEntity<SerieDTO> atualizarCarga(@PathVariable Long id, @RequestParam BigDecimal carga) {
        SerieDTO serie = service.atualizarCarga(id, carga);
        return ResponseEntity.ok(serie);
    }
}

