package model;
import enums.*;

import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
public class FormularioCompleto {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_crianca", nullable = false)
    private String nomeCrianca;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "rg", nullable = false)
    private String rg;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private Raca raca;

    @Enumerated(EnumType.STRING)
    private SimNao gemeo;

    @Enumerated(EnumType.STRING)
    private SimNao possuiIrmaoNaCreche;
    
    @Column(name = "cadastro_nacional_sus", nullable = false)
    private String cadastroNacionalSus;

    @Column(name = "unidade_saude", nullable = false)
    private String unidadeSaude;

    @Enumerated(EnumType.STRING)
    private SimNao problemasSaude;

    // ElementCollections
    @ElementCollection
    @CollectionTable(
        name = "formulario_restricao_alimentar",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "restricao")
    private Set<RestricaoAlimentar> restricaoAlimentar;

    @ElementCollection
    @CollectionTable(
        name = "formulario_alergias",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "alergia")
    private Set<Alergias> alergia;

    @Enumerated(EnumType.STRING)
    private Mobilidade mobilidade;

    @ElementCollection
    @CollectionTable(
        name = "formulario_educacao_especial",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "educacao_especial")
    private Set<EducacaoEspecial> educacaoEspecial;

    @Enumerated(EnumType.STRING)
    private SimNao recebeBeneficioSocial;

    @ElementCollection
    @CollectionTable(
        name = "formulario_auxilio",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "auxilio")
    private Set<Auxilio> auxilios;

    @Column(name = "nis", nullable = false)
    private String nis;

    @ManyToMany
    @JoinTable(
        name = "formulario_responsavel",  
        joinColumns = @JoinColumn(name = "formulario_id"),
        inverseJoinColumns = @JoinColumn(name = "responsavel_id")
    )
    private Set<Responsavel> responsavel;

    @Column(name = "municipio_nascimento", nullable = false)
    private String municipioNascimento;  

    @Column(name = "cartorio_registro", nullable = false)
    private String cartorioRegistro;

    @Column(name = "municipio_registro", nullable = false)
    private String municipioRegistro;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "data_emissao_rg", nullable = false)
    private LocalDate dataEmissaoRg;

    @Column(name = "orgao_emissor_rg", nullable = false)
    private String orgaoEmissorRg;

    @Column(name = "valor_aluguel", nullable = false)
    private BigDecimal valorAluguel; 

    @Column(name = "numero_comodos", nullable = false)
    private int numeroComodos;

    @Enumerated(EnumType.STRING)
    private SituacaoCasa situacaoCasa;

    @ElementCollection  
    @CollectionTable(
        name = "formulario_tipo_piso",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_piso")
    private Set<TipoPiso> tipoPiso;

    @ElementCollection  
    @CollectionTable(
        name = "formulario_tipo_moradia",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_moradia")
    private Set<TipoMoradia> tipoMoradia;

    @Enumerated(EnumType.STRING)
    private SimNao temFossa;

    @Enumerated(EnumType.STRING)
    private SimNao temCifon;

    @Enumerated(EnumType.STRING)
    private SimNao temEnergiaEletrica;

    @Enumerated(EnumType.STRING)
    private SimNao temAguaEncanada;

    @ElementCollection  
    @CollectionTable(
        name = "formulario_moveis_domicilio",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "moveis_domicilio")
    private Moveis noSeuDomicilioTem;

    @Column(name = "renda_familiar_mensal", nullable = false)
    private BigDecimal rendaFamiliarMensal; 

    @Column(name = "renda_per_capita", nullable = false)
    private BigDecimal rendaPerCapita;

    @ManyToMany
    @JoinTable(
        name = "formulario_composicao_familiar", 
        joinColumns = @JoinColumn(name = "formulario_id"),
        inverseJoinColumns = @JoinColumn(name = "composicao_familiar_id")
    )
    private Set<ComposicaoFamiliar> composicaoFamiliar;

    @Column(name = "serie_que_ira_cursar", nullable = false)
    private int serieQueIraCursar;

    @Column(name = "ano_letivo", nullable = false)
    private int anoLetivo;

    @ManyToMany
    @JoinTable(
        name = "formulario_pessoas_autorizadas", 
        joinColumns = @JoinColumn(name = "formulario_id"),
        inverseJoinColumns = @JoinColumn(name = "pessoas_autorizadas_id")
    )
    private Set<PessoasAutorizadas> pessoasAutorizadas;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Column(name = "data_desligamento", nullable = false)
    private LocalDate dataDesligamento;
}
