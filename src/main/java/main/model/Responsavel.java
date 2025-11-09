package main.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

@RequiredArgsConstructor
@Entity
public class Responsavel {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 80, message = "Nome deve ter no máximo 80 caracteres")
    @Pattern(regexp = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$", message = "Use apenas letras e espaços")
    @Column(nullable = false, length = 80)
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @Pattern(regexp = "\\d{5,15}", message = "RG deve conter apenas dígitos (5 a 15)")
    @Column(nullable = false, length = 15)
    private String rg;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD + número, apenas dígitos (10 a 11)")
    @Column(nullable = false, length = 11)
    private String telefone;

    @ManyToMany(mappedBy = "responsavel")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Set<FormularioCompleto> formularioCompleto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @Valid
    private Endereco endereco;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<FormularioCompleto> getFormularioCompleto() {
        return formularioCompleto;
    }

    public void setFormularioCompleto(Set<FormularioCompleto> formularioCompleto) {
        this.formularioCompleto = formularioCompleto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}