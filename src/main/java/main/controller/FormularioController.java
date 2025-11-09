package main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.service.FormularioService;
import main.model.FormularioCompleto;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;
import main.enums.Status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FormularioController {
    
    private final FormularioService formularioService;
    
    // ========== CRIAÇÃO DE FORMULÁRIO ==========
    @PostMapping("/formulario")
    public FormularioCompleto criarFormulario(@Valid @RequestBody FormularioCompleto formulario) {
        return formularioService.criarNovoFormulario(formulario);
    }

    // ========== BUSCA POR CPF ==========
    @GetMapping("/formulario/buscar/cpf/{cpf}")
    public ResponseEntity<FormularioCompleto> buscarFormularioPorCpf(@PathVariable String cpf) {
        Optional<FormularioCompleto> formulario = formularioService.buscarPorCpfComTodasInformacoes(cpf);
        return formulario
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== BUSCA POR STATUS ==========
    @GetMapping("/formularios/buscar/status/{status}")
    public List<FormularioCompleto> buscarFormulariosPorStatus(@PathVariable String status) {
        try {
            Status statusEnum = Status.valueOf(status.toUpperCase());
            return formularioService.buscarPorStatus(statusEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido: " + status + ". Use: PENDENTE, APROVADO, REPROVADO");
        }
    }

    // ========== LISTAR TODOS OS STATUS DISPONÍVEIS ==========
    @GetMapping("/formularios/status/opcoes")
    public List<String> listarStatusDisponiveis() {
        return List.of("PENDENTE", "APROVADO", "REPROVADO");
    }

    // ========== EDIÇÃO DE FORMULÁRIO EXISTENTE ==========
    @PutMapping("/formulario/editar/{id}")
    public ResponseEntity<FormularioCompleto> editarFormulario(
            @PathVariable Long id, 
            @Valid @RequestBody FormularioCompleto formularioAtualizado) {
        
        try {
            FormularioCompleto formularioEditado = formularioService.editarFormularioExistente(id, formularioAtualizado);
            return ResponseEntity.ok(formularioEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== BUSCAR FORMULÁRIO POR ID PARA EDIÇÃO ==========
    @GetMapping("/formulario/editar/{id}")
    public ResponseEntity<FormularioCompleto> buscarFormularioParaEdicao(@PathVariable Long id) {
        Optional<FormularioCompleto> formulario = formularioService.buscarPorIdParaEdicao(id);
        return formulario
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== LISTAR TODOS OS FORMULÁRIOS ==========
    @GetMapping("/formularios/completo")
    public List<FormularioCompleto> obterFormulariosCompletos() {
        return formularioService.listarTodosComTodasInformacoes();
    }
}
