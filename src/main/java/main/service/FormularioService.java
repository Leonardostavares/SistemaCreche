package main.service; 
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import main.repository.FormularioRepositorio;
import main.repository.ResponsavelRepositorio;
import main.model.FormularioCompleto;
import main.model.Responsavel;
import main.enums.Status;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class FormularioService {

    private final FormularioRepositorio formularioRepositorio;
    private final ResponsavelRepositorio responsavelRepositorio;

    /**
     * CRIAÇÃO: Cria um novo formulário (sem ID)
     */
    public FormularioCompleto criarNovoFormulario(FormularioCompleto formulario) {
        // Garantir que é um novo formulário (sem ID)
        formulario.setId(null);
        
        // O status agora será definido pelo usuário no frontend (não mais automático)
        // Se não foi definido status, usar PENDENTE como padrão
        if (formulario.getStatus() == null) {
            formulario.setStatus(Status.PENDENTE);
        }
        
        // PROCESSAR RESPONSÁVEIS: Reutilizar responsáveis existentes baseado no CPF
        if (formulario.getResponsavel() != null && !formulario.getResponsavel().isEmpty()) {
            Set<Responsavel> responsaveisProcessados = processarResponsaveis(formulario.getResponsavel());
            formulario.setResponsavel(responsaveisProcessados);
        }
        
        // Definir referências bidirecionais para evitar problemas de cascade
        if (formulario.getComposicaoFamiliar() != null) {
            formulario.getComposicaoFamiliar().forEach(cf -> cf.setFormularioCompleto(formulario));
        }
        
        if (formulario.getPessoasAutorizadas() != null) {
            formulario.getPessoasAutorizadas().forEach(pa -> pa.setFormularioCompleto(formulario));
        }
        
        return formularioRepositorio.save(formulario);
    }

    /**
     * PROCESSAMENTO DE RESPONSÁVEIS: Reutiliza responsáveis existentes baseado no CPF
     */
    private Set<Responsavel> processarResponsaveis(Set<Responsavel> responsaveisOriginais) {
        Set<Responsavel> responsaveisProcessados = new HashSet<>();
        
        for (Responsavel responsavelOriginal : responsaveisOriginais) {
            if (responsavelOriginal.getCpf() != null && !responsavelOriginal.getCpf().trim().isEmpty()) {
                // Buscar responsável existente pelo CPF
                Optional<Responsavel> responsavelExistente = responsavelRepositorio.findByCpf(responsavelOriginal.getCpf());
                
                if (responsavelExistente.isPresent()) {
                    // REUTILIZAR responsável existente
                    responsaveisProcessados.add(responsavelExistente.get());
                } else {
                    // CRIAR novo responsável (CPF não existe)
                    responsavelOriginal.setId(null); // Garantir que será criado um novo
                    responsaveisProcessados.add(responsavelOriginal);
                }
            } else {
                // CPF não informado, criar normalmente
                responsavelOriginal.setId(null);
                responsaveisProcessados.add(responsavelOriginal);
            }
        }
        
        return responsaveisProcessados;
    }

    /**
     * EDIÇÃO: Edita um formulário existente usando seu ID
     */
    public FormularioCompleto editarFormularioExistente(Long id, FormularioCompleto formularioAtualizado) {
        // Verificar se o formulário existe
        Optional<FormularioCompleto> formularioExistente = formularioRepositorio.findById(id);
        if (formularioExistente.isEmpty()) {
            throw new IllegalArgumentException("Formulário com ID " + id + " não encontrado");
        }
        
        // Garantir que o ID está correto
        formularioAtualizado.setId(id);
        
        // PROCESSAR RESPONSÁVEIS: Reutilizar responsáveis existentes baseado no CPF
        if (formularioAtualizado.getResponsavel() != null && !formularioAtualizado.getResponsavel().isEmpty()) {
            Set<Responsavel> responsaveisProcessados = processarResponsaveis(formularioAtualizado.getResponsavel());
            formularioAtualizado.setResponsavel(responsaveisProcessados);
        }
        
        // Definir referências bidirecionais corretamente
        if (formularioAtualizado.getComposicaoFamiliar() != null) {
            formularioAtualizado.getComposicaoFamiliar().forEach(cf -> cf.setFormularioCompleto(formularioAtualizado));
        }
        
        if (formularioAtualizado.getPessoasAutorizadas() != null) {
            formularioAtualizado.getPessoasAutorizadas().forEach(pa -> pa.setFormularioCompleto(formularioAtualizado));
        }
        
        return formularioRepositorio.save(formularioAtualizado);
    }

    /**
     * BUSCA: Busca um formulário por ID para edição (com todas as relações)
     */
    public Optional<FormularioCompleto> buscarPorIdParaEdicao(Long id) {
        return formularioRepositorio.findById(id);
    }

    /**
     * BUSCA: Busca formulários por status
     */
    public List<FormularioCompleto> buscarPorStatus(main.enums.Status status) {
        return formularioRepositorio.findByStatus(status);
    }

    /**
     * LEGADO: Mantido para compatibilidade
     */
    public FormularioCompleto salvarFormulario(FormularioCompleto formulario) {
        return formularioRepositorio.save(formulario);
    }

    /**
     * LEGADO: Mantido para compatibilidade  
     */
    public FormularioCompleto atualizarFormulario(FormularioCompleto formulario) {
        // Verifica se o formulário existe
        if (formulario.getId() == null) {
            throw new IllegalArgumentException("ID do formulário é obrigatório para atualização");
        }
        
        // Para entidades com relacionamentos complexos, precisamos definir as referências de volta
        // corretamente para evitar problemas de cascade
        if (formulario.getComposicaoFamiliar() != null) {
            formulario.getComposicaoFamiliar().forEach(cf -> cf.setFormularioCompleto(formulario));
        }
        
        if (formulario.getPessoasAutorizadas() != null) {
            formulario.getPessoasAutorizadas().forEach(pa -> pa.setFormularioCompleto(formulario));
        }
        
        return formularioRepositorio.save(formulario);
    }

    /**
     * Lista todos os formulários (SEM carregar as relações - será vazio)
     */
    public List<FormularioCompleto> listarFormularios() {
        return formularioRepositorio.findAll();
    }
    
    /**
     * Busca TODOS os formulários pelo CPF de QUALQUER responsável
     * Retorna todas as informações: responsáveis, endereços, composição familiar, pessoas autorizadas
     * AGORA RETORNA LISTA: Um responsável pode ter múltiplos formulários (vários filhos)
     */
    public List<FormularioCompleto> buscarPorCpfComTodasInformacoes(String cpf) {
        return formularioRepositorio.findByCpfResponsavelComTodasInformacoes(cpf);
    }
    
    /**
     * Lista TODOS os formulários COM TODAS as relações carregadas
     */
    public List<FormularioCompleto> listarTodosComTodasInformacoes() {
        return formularioRepositorio.findAllComTodasInformacoes();
    }
    
    /**
     * Valida se os campos mínimos obrigatórios estão preenchidos
     * Para uma creche pública, são essenciais: nome da criança, data de nascimento, 
     * pelo menos um responsável com nome e telefone, e endereço básico
     */
    public boolean validarCamposMinimos(FormularioCompleto formulario) {
        // Valida dados básicos da criança
        if (formulario.getNomeCrianca() == null || formulario.getNomeCrianca().trim().isEmpty()) {
            return false;
        }
        if (formulario.getDataNascimento() == null) {
            return false;
        }
        
        // Valida se há pelo menos um responsável
        if (formulario.getResponsavel() == null || formulario.getResponsavel().isEmpty()) {
            return false;
        }
        
        // Valida se o responsável tem dados mínimos
        return formulario.getResponsavel().stream().anyMatch(responsavel -> 
            responsavel.getNome() != null && !responsavel.getNome().trim().isEmpty() &&
            responsavel.getTelefone() != null && !responsavel.getTelefone().trim().isEmpty() &&
            responsavel.getEndereco() != null &&
            responsavel.getEndereco().getEndereco() != null && 
            !responsavel.getEndereco().getEndereco().trim().isEmpty()
        );
    }
    
    /**
     * Salva um formulário após validar os campos mínimos obrigatórios
     */
    public FormularioCompleto salvarFormularioComValidacao(FormularioCompleto formulario) {
        if (!validarCamposMinimos(formulario)) {
            throw new IllegalArgumentException("Os campos mínimos obrigatórios não foram preenchidos: Nome da criança e Data de nascimento");
        }
        return formularioRepositorio.save(formulario);
    }
}
