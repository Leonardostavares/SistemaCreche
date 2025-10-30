package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import service.FormularioService;
import model.FormularioCompleto;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FormularioController {
    
    private final FormularioService formularioService;


    @PostMapping("/formulario")
    public FormularioCompleto enviarFormulario(@RequestBody FormularioCompleto formulario) {
        return formularioService.salvarFormulario(formulario);
    }

}
