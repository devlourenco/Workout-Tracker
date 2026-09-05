package br.com.guilherme.workout_tracker.controller;

import br.com.guilherme.workout_tracker.dto.TreinoDTO;
import br.com.guilherme.workout_tracker.dto.TreinoUpdateDTO;
import br.com.guilherme.workout_tracker.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Treinos",
        description = "Operações para gerenciamento de treinos"
)
@RestController
@RequestMapping("/treinos")
public class TreinoController {
    private final TreinoService service;

    public TreinoController(TreinoService service) {
        this.service = service;
    }


    @Operation(
            summary = "Cadastrar treino",
            description = "Cadastra um novo treino com seus exercícios e séries"
    )
    @PostMapping
    public ResponseEntity<TreinoDTO> cadastrarTreino(@Valid @RequestBody TreinoDTO treinoDTO) {
        TreinoDTO treino = service.cadastrarTreino(treinoDTO);

        return ResponseEntity.ok(treino);
    }

    @Operation(
            summary = "Listar treinos",
            description = "Retorna todos os treinos cadastrados"
    )
    @GetMapping
    public ResponseEntity<List<TreinoDTO>> listarTreinos() {
        List<TreinoDTO> treinos = service.listarTreinos();
        return ResponseEntity.ok(treinos);
    }

    @Operation(
            summary = "Buscar treino por ID",
            description = "Retorna um treino específico pelo seu identificador"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TreinoDTO> listarPorId(@PathVariable Long id) {
        TreinoDTO treino = service.listarPorId(id);
        return ResponseEntity.ok(treino);
    }

    @Operation(
            summary = "Atualizar treino",
            description = "Atualiza parcialmente o nome ou o dia da semana de um treino"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<TreinoDTO> atualizarTreino(@PathVariable Long id, @Valid @RequestBody TreinoUpdateDTO treinoDTO) {
        TreinoDTO treino = service.atualizarTreino(id, treinoDTO);
        return ResponseEntity.ok(treino);
    }

    @Operation(
            summary = "Excluir treino",
            description = "Remove um treino pelo seu identificador"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<TreinoDTO> deletarTreino(@PathVariable Long id) {
        service.deletarTreino(id);
        return ResponseEntity.noContent().build();

    }
}
