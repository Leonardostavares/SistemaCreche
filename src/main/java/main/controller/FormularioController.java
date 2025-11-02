package main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FormularioController {
    
    private final FormularioService formularioService;

    /**
     * Cria um novo formulário
     * POST /api/formulario
     */
    @PostMapping("/formulario")
    public FormularioCompleto enviarFormulario(@Valid @RequestBody FormularioCompleto formulario) {
        return formularioService.salvarFormulario(formulario);
    }

    /**
     * Lista todos os formulários (SEM as relações - será vazio)
     * GET /api/formularios
     */
    @GetMapping("/formularios")
    public List<FormularioCompleto> obterFormularios() {
        return formularioService.listarFormularios();
    }
    
    /**
     * Busca um formulário completo pelo CPF de QUALQUER responsável
     * Retorna TODAS as informações: responsáveis, endereços, composição familiar, pessoas autorizadas
     * GET /api/formulario/cpf/{cpf}
     * Exemplo: GET /api/formulario/cpf/12345678901
     */
    @GetMapping("/formulario/cpf/{cpf}")
    public ResponseEntity<FormularioCompleto> buscarPorCpf(@PathVariable String cpf) {
        Optional<FormularioCompleto> formulario = formularioService.buscarPorCpfComTodasInformacoes(cpf);
        return formulario
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Lista TODOS os formulários COM TODAS as relações carregadas
     * GET /api/formularios/completo
     */
    @GetMapping("/formularios/completo")
    public List<FormularioCompleto> obterFormulariosCompletos() {
        return formularioService.listarTodosComTodasInformacoes();
    }
}

