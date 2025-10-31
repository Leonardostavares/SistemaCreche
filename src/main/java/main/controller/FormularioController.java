package main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import main.service.FormularioService;
import main.model.FormularioCompleto;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FormularioController {
    
    private final FormularioService formularioService;

    @PostMapping("/formulario")
    public FormularioCompleto enviarFormulario(@RequestBody FormularioCompleto formulario) {
        return formularioService.salvarFormulario(formulario);
    }

    @GetMapping("/formularios")
    public List<FormularioCompleto> obterFormularios() {
        return formularioService.listarFormularios();
    }
}

