package main.service;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import main.repository.FormularioRepositorio;
import main.model.FormularioCompleto;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FormularioService {

    private final FormularioRepositorio formularioRepositorio;

    public FormularioCompleto salvarFormulario(FormularioCompleto formulario) {
        return formularioRepositorio.save(formulario);
    }

    public List<FormularioCompleto> listarFormularios() {
        return formularioRepositorio.findAll();
    }
}
