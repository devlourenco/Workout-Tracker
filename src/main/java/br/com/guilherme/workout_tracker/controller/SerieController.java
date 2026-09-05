package br.com.guilherme.workout_tracker.controller;

import br.com.guilherme.workout_tracker.dto.SerieDTO;
import br.com.guilherme.workout_tracker.service.SerieService;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Tag(
        name = "Séries",
        description = "Operações para consulta e atualização de séries"
)
@Validated
@RestController
@RequestMapping("/series")
public class SerieController {
    private final SerieService service;

    public SerieController(SerieService service) {
        this.service = service;
    }

    @Operation(
            summary = "Buscar série por ID",
            description = "Retorna uma série específica pelo seu identificador"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> listarPorId(@PathVariable Long id) {
        SerieDTO serie = service.listarPorId(id);
        return ResponseEntity.ok(serie);
    }

    @Operation(
            summary = "Atualizar RIR",
            description = "Atualiza o RIR de uma série existente"
    )
    @PatchMapping("/{id}/rir")
    public ResponseEntity<SerieDTO> atualizarRir(@PathVariable Long id, @RequestParam @Min(value = 0, message = "O RIR não pode ser negativo") Integer rir) {
        SerieDTO serie = service.atualizarRir(id, rir);
        return ResponseEntity.ok(serie);
    }

    @Operation(
            summary = "Atualizar repetições",
            description = "Atualiza o número de repetições de uma série existente"
    )
    @PatchMapping("/{id}/reps")
    public ResponseEntity<SerieDTO> atualizarReps(@PathVariable Long id, @RequestParam @Min(value = 1, message = "As repetições não podem ser negativas") Integer reps) {
        SerieDTO serie = service.atualizarReps(id, reps);
        return ResponseEntity.ok(serie);
    }

    @Operation(
            summary = "Atualizar carga",
            description = "Atualiza a carga de uma série existente"
    )
    @PatchMapping("/{id}/carga")
    public ResponseEntity<SerieDTO> atualizarCarga(@PathVariable Long id, @RequestParam @DecimalMin(value = "0.0", message = "A carga não pode ser negativa") BigDecimal carga) {
        SerieDTO serie = service.atualizarCarga(id, carga);
        return ResponseEntity.ok(serie);
    }
}

