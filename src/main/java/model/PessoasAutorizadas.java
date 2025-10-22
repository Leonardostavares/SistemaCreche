package model;
import enums.Parentesco;
import jakarta.persistence.Enumerated;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class PessoasAutorizadas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;    

    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String telefoneContato;

    @ManyToMany(mappedBy = "pessoasAutorizadas")
    private Set<FormularioCompleto> formularioCompleto;
}
