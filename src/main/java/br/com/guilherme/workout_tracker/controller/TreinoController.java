package br.com.guilherme.workout_tracker.controller;

import br.com.guilherme.workout_tracker.dto.TreinoDTO;
import br.com.guilherme.workout_tracker.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {
    private final TreinoService service;

    public TreinoController(TreinoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TreinoDTO> cadastrarTreino(@Valid @RequestBody TreinoDTO treinoDTO) {
        TreinoDTO treino = service.cadastrarTreino(treinoDTO);

        return ResponseEntity.ok(treino);
    }

    @GetMapping
    public ResponseEntity<List<TreinoDTO>> listarTreinos() {
        List<TreinoDTO> treinos = service.listarTreinos();
        return ResponseEntity.ok(treinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoDTO> listarPorId(@PathVariable Long id) {
        TreinoDTO treino = service.listarPorId(id);
        return ResponseEntity.ok(treino);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TreinoDTO> atualizarTreino(@PathVariable Long id, @RequestBody TreinoDTO treinoDTO) {
        TreinoDTO treino = service.atualizarTreino(id, treinoDTO);
        return ResponseEntity.ok(treino);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TreinoDTO> deletarTreino(@PathVariable Long id) {
        service.deletarTreino(id);
        return ResponseEntity.noContent().build();

    }
}
