package main.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Set;
@Entity
public class ComposicaoFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(max = 80, message = "Nome completo deve ter no máximo 80 caracteres")
    @Pattern(regexp = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$", message = "Use apenas letras e espaços")
    @Column(nullable = false, length = 80)
    private String nomeCompleto;

    @Min(value = 0, message = "Idade não pode ser negativa")
    @Column(nullable = false)
    private int idade;

    @NotBlank(message = "Grau de parentesco é obrigatório")
    @Size(max = 40, message = "Grau de parentesco deve ter no máximo 40 caracteres")
    @Pattern(regexp = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$", message = "Use apenas letras e espaços")
    @Column(nullable = false, length = 40)
    private String grauParentesco;

    @NotBlank(message = "Situação escolar é obrigatória")
    @Size(max = 40, message = "Situação escolar deve ter no máximo 40 caracteres")
    @Column(nullable = false, length = 40)
    private String situacaoEscolar;

    @NotBlank(message = "Situação de emprego é obrigatória")
    @Size(max = 40, message = "Situação de emprego deve ter no máximo 40 caracteres")
    @Column(nullable = false, length = 40)
    private String situacaoEmprego;
    
    @PositiveOrZero(message = "Salário deve ser zero ou positivo")
    @Column(nullable = false)
    private double salario;

    @ManyToMany(mappedBy = "composicaoFamiliar")
    private Set<FormularioCompleto> formularioCompleto;

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public int getIdade() {
        return idade;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public String getSituacaoEscolar() {
        return situacaoEscolar;
    }

    public String getSituacaoEmprego() {
        return situacaoEmprego;
    }

    public double getSalario() {
        return salario;
    }

    public Set<FormularioCompleto> getFormularioCompleto() {
        return formularioCompleto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public void setSituacaoEscolar(String situacaoEscolar) {
        this.situacaoEscolar = situacaoEscolar;
    }

    public void setSituacaoEmprego(String situacaoEmprego) {
        this.situacaoEmprego = situacaoEmprego;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setFormularioCompleto(Set<FormularioCompleto> formularioCompleto) {
        this.formularioCompleto = formularioCompleto;
    }

    


}