package model;
import jakarta.persistence.*;
import java.util.Set;
@Entity
public class ComposicaoFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private String grauParentesco;

    @Column(nullable = false)
    private String situacaoEscolar;

    @Column(nullable = false)
    private String situacaoEmprego;
    
    @Column(nullable = false)
    private double salario;

    @ManyToMany(mappedBy = "composicaoFamiliar")
    private Set<FormularioCompleto> formularioCompleto;


}