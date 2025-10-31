package main.model;

import main.enums.Parentesco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class PessoasAutorizadas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(max = 80, message = "Nome completo deve ter no máximo 80 caracteres")
    @Pattern(regexp = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$", message = "Use apenas letras e espaços")
    @Column(nullable = false, length = 80)
    private String nomeCompleto;

    @NotNull(message="Parentesco não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    @NotBlank(message = "RG é obrigatório")
    @Pattern(regexp = "\\d{5,12}", message = "RG deve conter apenas dígitos (5 a 12)")
    @Column(nullable = false, length = 12)
    private String rg;

    @NotBlank(message = "Telefone de contato é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD + número, apenas dígitos (10 a 11)")
    @Column(nullable = false, length = 11)
    private String telefoneContato;

    @ManyToMany(mappedBy = "pessoasAutorizadas")
    private Set<FormularioCompleto> formularioCompleto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public Set<FormularioCompleto> getFormularioCompleto() {
        return formularioCompleto;
    }

    public void setFormularioCompleto(Set<FormularioCompleto> formularioCompleto) {
        this.formularioCompleto = formularioCompleto;
    }

    

}
