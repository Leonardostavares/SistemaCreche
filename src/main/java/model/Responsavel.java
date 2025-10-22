package model;
import jakarta.persistence.*;


import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
@Entity
public class Responsavel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String nomeMae;

    @Column(nullable = false)
    private String telefoneMae;

    @Column(nullable = false)
    private String cpfMae;

    @Column(nullable = false)
    private String rgMae;

    @Column(nullable = false)
    private String localTrabalhoMae;

    @Column(nullable = false)
    private String telefone2Mae;

    @Column(nullable = false)
    private String nomePai;

    @Column(nullable = false)
    private String telefonePai;

    @Column(nullable = false)
    private String cpfPai;

    @Column(nullable = false)
    private String rgPai;

    @Column(nullable = false)
    private String localTrabalhoPai;

    @Column(nullable = false)
    private String telefone2Pai;

    @Column(nullable = false)
    private String nomeResponsavel;

    @Column(nullable = false)
    private String telefoneResponsavel;

    @Column(nullable = false)  
    private String cpfResponsavel;

    @Column(nullable = false)
    private String rgResponsavel;

    @Column(nullable = false)
    private String localTrabalhoResponsavel;

    @ManyToMany(mappedBy = "responsavel")
    private Set<FormularioCompleto> formularioCompleto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

}