package br.com.guilherme.workout_tracker.controller;


import br.com.guilherme.workout_tracker.dto.ExercicioDTO;
import br.com.guilherme.workout_tracker.enums.GrupoMuscularEnum;
import br.com.guilherme.workout_tracker.service.ExercicioService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Exercícios",
        description = "Operações para consulta e atualização de exercícios"
)
@Validated
@RestController
@RequestMapping("/exercicios")
public class ExercicioController {
    private final ExercicioService service;

    public ExercicioController(ExercicioService service) {
        this.service = service;
    }

    @Operation(
            summary = "Buscar exercício por ID",
            description = "Retorna um exercício específico pelo seu identificador"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExercicioDTO> listarPorId(@PathVariable Long id) {
        ExercicioDTO exercicio = service.listarPorId(id);
        return ResponseEntity.ok(exercicio);
    }


    @Operation(
            summary = "Atualizar nome do exercício",
            description = "Atualiza o nome de um exercício existente"
    )
    @PatchMapping("/{id}/nome")    public ResponseEntity<ExercicioDTO> atualizarNome(@PathVariable Long id, @RequestParam @NotBlank(message = "O nome do exercício é obrigatório") String nome) {
        ExercicioDTO exercicio = service.atualizarNome(id, nome);
        return ResponseEntity.ok(exercicio);
    }

    @Operation(
            summary = "Atualizar grupo muscular",
            description = "Atualiza o grupo muscular associado a um exercício"
    )
    @PatchMapping("/{id}/grupo-muscular")    public ResponseEntity<ExercicioDTO> atualizarGrupoMuscular(@PathVariable Long id, @RequestParam GrupoMuscularEnum grupoMuscular) {
        ExercicioDTO exercicio = service.atualizarGrupoMuscular(id, grupoMuscular);
        return ResponseEntity.ok(exercicio);
    }

}



