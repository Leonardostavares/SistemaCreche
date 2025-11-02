package main.service; 
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import main.repository.FormularioRepositorio;
import main.model.FormularioCompleto;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FormularioService {

    private final FormularioRepositorio formularioRepositorio;

    /**
     * Salva um novo formulário com todas as suas relações
     */
    public FormularioCompleto salvarFormulario(FormularioCompleto formulario) {
        return formularioRepositorio.save(formulario);
    }

    /**
     * Lista todos os formulários (SEM carregar as relações - será vazio)
     */
    public List<FormularioCompleto> listarFormularios() {
        return formularioRepositorio.findAll();
    }
    
    /**
     * Busca um formulário completo pelo CPF de QUALQUER responsável
     * Retorna todas as informações: responsáveis, endereços, composição familiar, pessoas autorizadas
     */
    public Optional<FormularioCompleto> buscarPorCpfComTodasInformacoes(String cpf) {
        return formularioRepositorio.findByCpfResponsavelComTodasInformacoes(cpf);
    }
    
    /**
     * Lista TODOS os formulários COM TODAS as relações carregadas
     */
    public List<FormularioCompleto> listarTodosComTodasInformacoes() {
        return formularioRepositorio.findAllComTodasInformacoes();
    }
}
