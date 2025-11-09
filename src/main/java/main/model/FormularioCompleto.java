package main.model;
import main.enums.*;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
public class FormularioCompleto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da criança é obrigatório")
    @Size(max = 80, message = "Nome deve ter no máximo 80 caracteres")
    @Column(name = "nome_crianca", nullable = false)
    private String nomeCrianca;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "rg", nullable = true)
    private String rg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Raca raca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SimNao gemeo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SimNao possuiIrmaoNaCreche;
    
    @Column(name = "cadastro_nacional_sus", nullable = true)
    private String cadastroNacionalSus;

    @Column(name = "unidade_saude", nullable = true)
    private String unidadeSaude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SimNao problemasSaude;

    // ElementCollections
    @ElementCollection
    @CollectionTable(
        name = "formulario_restricao_alimentar",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "restricao")
    private Set<RestricaoAlimentar> restricaoAlimentar = new HashSet<>();

    @ElementCollection
    @CollectionTable(
        name = "formulario_alergias",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "alergia")
    private Set<Alergias> alergia = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Mobilidade mobilidade;

    @ElementCollection
    @CollectionTable(
        name = "formulario_educacao_especial",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "educacao_especial")
    private Set<EducacaoEspecial> educacaoEspecial = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SimNao recebeBeneficioSocial;

    @ElementCollection
    @CollectionTable(
        name = "formulario_auxilio",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "auxilio")
    private Set<Auxilio> auxilios = new HashSet<>();

    @Column(name = "nis", nullable = true)
    private String nis;
    
    @Valid
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "formulario_responsavel",  
        joinColumns = @JoinColumn(name = "formulario_id"),
        inverseJoinColumns = @JoinColumn(name = "responsavel_id")
    )
    private Set<Responsavel> responsavel = new HashSet<>();

    @Column(name = "municipio_nascimento", nullable = true)
    private String municipioNascimento;  

    @Column(name = "cartorio_registro", nullable = true)
    private String cartorioRegistro;

    @Column(name = "municipio_registro", nullable = true)
    private String municipioRegistro;

    @NotBlank(message = "CPF da criança é obrigatório")
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "data_emissao_rg", nullable = true)
    private LocalDate dataEmissaoRg;

    @Column(name = "orgao_emissor_rg", nullable = true)
    private String orgaoEmissorRg;

    @Column(name = "valor_aluguel", nullable = true)
    private BigDecimal valorAluguel; 

    @Column(name = "numero_comodos", nullable = true)
    private int numeroComodos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
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
    @Column(nullable = true)
    private SimNao temFossa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SimNao temCifon;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SimNao temEnergiaEletrica;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SimNao temAguaEncanada;

    @ElementCollection  
    @CollectionTable(
        name = "formulario_moveis_domicilio",
        joinColumns = @JoinColumn(name = "formulario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "moveis_domicilio")
    private Set<Moveis> noSeuDomicilioTem = new HashSet<>();

    @Column(name = "renda_familiar_mensal", nullable = true)
    private BigDecimal rendaFamiliarMensal; 

    @Column(name = "renda_per_capita", nullable = true)
    private BigDecimal rendaPerCapita;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "formulario_id")
    private Set<ComposicaoFamiliar> composicaoFamiliar = new HashSet<>();

    @Column(name = "serie_que_ira_cursar", nullable = true)
    private int serieQueIraCursar;

    @Column(name = "ano_letivo", nullable = true)
    private int anoLetivo;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "formulario_id")
    private Set<PessoasAutorizadas> pessoasAutorizadas = new HashSet<>();

    @Column(name = "data_matricula", nullable = true)
    private LocalDate dataMatricula;

    @Column(name = "data_desligamento", nullable = true)
    private LocalDate dataDesligamento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 20)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCrianca() {
        return nomeCrianca;
    }

    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public SimNao getGemeo() {
        return gemeo;
    }

    public void setGemeo(SimNao gemeo) {
        this.gemeo = gemeo;
    }

    public SimNao getPossuiIrmaoNaCreche() {
        return possuiIrmaoNaCreche;
    }

    public void setPossuiIrmaoNaCreche(SimNao possuiIrmaoNaCreche) {
        this.possuiIrmaoNaCreche = possuiIrmaoNaCreche;
    }

    public String getCadastroNacionalSus() {
        return cadastroNacionalSus;
    }

    public void setCadastroNacionalSus(String cadastroNacionalSus) {
        this.cadastroNacionalSus = cadastroNacionalSus;
    }

    public String getUnidadeSaude() {
        return unidadeSaude;
    }

    public void setUnidadeSaude(String unidadeSaude) {
        this.unidadeSaude = unidadeSaude;
    }

    public SimNao getProblemasSaude() {
        return problemasSaude;
    }

    public void setProblemasSaude(SimNao problemasSaude) {
        this.problemasSaude = problemasSaude;
    }

    public Set<RestricaoAlimentar> getRestricaoAlimentar() {
        return restricaoAlimentar;
    }

    public void setRestricaoAlimentar(Set<RestricaoAlimentar> restricaoAlimentar) {
        this.restricaoAlimentar = restricaoAlimentar;
    }

    public Set<Alergias> getAlergia() {
        return alergia;
    }

    public void setAlergia(Set<Alergias> alergia) {
        this.alergia = alergia;
    }

    public Mobilidade getMobilidade() {
        return mobilidade;
    }

    public void setMobilidade(Mobilidade mobilidade) {
        this.mobilidade = mobilidade;
    }

    public Set<EducacaoEspecial> getEducacaoEspecial() {
        return educacaoEspecial;
    }

    public void setEducacaoEspecial(Set<EducacaoEspecial> educacaoEspecial) {
        this.educacaoEspecial = educacaoEspecial;
    }

    public SimNao getRecebeBeneficioSocial() {
        return recebeBeneficioSocial;
    }

    public void setRecebeBeneficioSocial(SimNao recebeBeneficioSocial) {
        this.recebeBeneficioSocial = recebeBeneficioSocial;
    }

    public Set<Auxilio> getAuxilios() {
        return auxilios;
    }

    public void setAuxilios(Set<Auxilio> auxilios) {
        this.auxilios = auxilios;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public Set<Responsavel> getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Set<Responsavel> responsavel) {
        this.responsavel = responsavel;
    }

    public String getMunicipioNascimento() {
        return municipioNascimento;
    }

    public void setMunicipioNascimento(String municipioNascimento) {
        this.municipioNascimento = municipioNascimento;
    }

    public String getCartorioRegistro() {
        return cartorioRegistro;
    }

    public void setCartorioRegistro(String cartorioRegistro) {
        this.cartorioRegistro = cartorioRegistro;
    }

    public String getMunicipioRegistro() {
        return municipioRegistro;
    }

    public void setMunicipioRegistro(String municipioRegistro) {
        this.municipioRegistro = municipioRegistro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataEmissaoRg() {
        return dataEmissaoRg;
    }

    public void setDataEmissaoRg(LocalDate dataEmissaoRg) {
        this.dataEmissaoRg = dataEmissaoRg;
    }

    public String getOrgaoEmissorRg() {
        return orgaoEmissorRg;
    }

    public void setOrgaoEmissorRg(String orgaoEmissorRg) {
        this.orgaoEmissorRg = orgaoEmissorRg;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public int getNumeroComodos() {
        return numeroComodos;
    }

    public void setNumeroComodos(int numeroComodos) {
        this.numeroComodos = numeroComodos;
    }

    public SituacaoCasa getSituacaoCasa() {
        return situacaoCasa;
    }

    public void setSituacaoCasa(SituacaoCasa situacaoCasa) {
        this.situacaoCasa = situacaoCasa;
    }

    public Set<TipoPiso> getTipoPiso() {
        return tipoPiso;
    }

    public void setTipoPiso(Set<TipoPiso> tipoPiso) {
        this.tipoPiso = tipoPiso;
    }

    public Set<TipoMoradia> getTipoMoradia() {
        return tipoMoradia;
    }

    public void setTipoMoradia(Set<TipoMoradia> tipoMoradia) {
        this.tipoMoradia = tipoMoradia;
    }

    public SimNao getTemFossa() {
        return temFossa;
    }

    public void setTemFossa(SimNao temFossa) {
        this.temFossa = temFossa;
    }

    public SimNao getTemCifon() {
        return temCifon;
    }

    public void setTemCifon(SimNao temCifon) {
        this.temCifon = temCifon;
    }

    public SimNao getTemEnergiaEletrica() {
        return temEnergiaEletrica;
    }

    public void setTemEnergiaEletrica(SimNao temEnergiaEletrica) {
        this.temEnergiaEletrica = temEnergiaEletrica;
    }

    public SimNao getTemAguaEncanada() {
        return temAguaEncanada;
    }

    public void setTemAguaEncanada(SimNao temAguaEncanada) {
        this.temAguaEncanada = temAguaEncanada;
    }

    public Set<Moveis> getNoSeuDomicilioTem() {
        return noSeuDomicilioTem;
    }

    public void setNoSeuDomicilioTem(Set<Moveis> noSeuDomicilioTem) {
        this.noSeuDomicilioTem = noSeuDomicilioTem;
    }

    public BigDecimal getRendaFamiliarMensal() {
        return rendaFamiliarMensal;
    }

    public void setRendaFamiliarMensal(BigDecimal rendaFamiliarMensal) {
        this.rendaFamiliarMensal = rendaFamiliarMensal;
    }

    public BigDecimal getRendaPerCapita() {
        return rendaPerCapita;
    }

    public void setRendaPerCapita(BigDecimal rendaPerCapita) {
        this.rendaPerCapita = rendaPerCapita;
    }

    public Set<ComposicaoFamiliar> getComposicaoFamiliar() {
        return composicaoFamiliar;
    }

    public void setComposicaoFamiliar(Set<ComposicaoFamiliar> composicaoFamiliar) {
        this.composicaoFamiliar = composicaoFamiliar;
    }

    public int getSerieQueIraCursar() {
        return serieQueIraCursar;
    }

    public void setSerieQueIraCursar(int serieQueIraCursar) {
        this.serieQueIraCursar = serieQueIraCursar;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Set<PessoasAutorizadas> getPessoasAutorizadas() {
        return pessoasAutorizadas;
    }

    public void setPessoasAutorizadas(Set<PessoasAutorizadas> pessoasAutorizadas) {
        this.pessoasAutorizadas = pessoasAutorizadas;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public LocalDate getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(LocalDate dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    


}
