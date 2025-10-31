package main.model;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    
    @NotBlank(message = "Nome da mãe é obrigatório")
    @Size(max = 80, message = "Nome da mãe deve ter no máximo 80 caracteres")
    @Pattern(regexp = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$", message = "Use apenas letras e espaços")
    @Column(nullable = false, length = 80)
    private String nomeMae;

    @NotBlank(message = "Telefone da mãe é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD + número, apenas dígitos (10 a 11)")
    @Column(nullable = false, length = 11)
    private String telefoneMae;

    @NotBlank(message = "CPF da mãe é obrigatório")
    @CPF(message = "CPF inválido")
    @Column(nullable = false, length = 11)
    private String cpfMae;

    @NotBlank(message = "RG da mãe é obrigatório")
    @Pattern(regexp = "\\d{5,12}", message = "RG deve conter apenas dígitos (5 a 12)")
    @Column(nullable = false, length = 12)
    private String rgMae;

    @NotBlank(message = "Local de trabalho da mãe é obrigatório")
    @Size(max = 120, message = "Local de trabalho da mãe deve ter no máximo 120 caracteres")
    @Column(nullable = false, length = 120)
    private String localTrabalhoMae;

    @NotBlank(message = "Telefone 2 da mãe é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD + número, apenas dígitos (10 a 11)")
    @Column(nullable = false, length = 11)
    private String telefone2Mae;

    @NotBlank(message = "Nome do pai é obrigatório")
    @Size(max = 80, message = "Nome do pai deve ter no máximo 80 caracteres")
    @Pattern(regexp = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$", message = "Use apenas letras e espaços")
    @Column(nullable = false, length = 80)
    private String nomePai;

    @NotBlank(message = "Telefone do pai é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD + número, apenas dígitos (10 a 11)")
    @Column(nullable = false, length = 11)
    private String telefonePai;

    @NotBlank(message = "CPF do pai é obrigatório")
    @CPF(message = "CPF inválido")
    @Column(nullable = false, length = 11)
    private String cpfPai;

    @NotBlank(message = "RG do pai é obrigatório")
    @Pattern(regexp = "\\d{5,12}", message = "RG deve conter apenas dígitos (5 a 12)")
    @Column(nullable = false, length = 12)
    private String rgPai;

    @NotBlank(message = "Local de trabalho do pai é obrigatório")
    @Size(max = 120, message = "Local de trabalho do pai deve ter no máximo 120 caracteres")
    @Column(nullable = false, length = 120)
    private String localTrabalhoPai;

    @NotBlank(message = "Telefone 2 do pai é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD + número, apenas dígitos (10 a 11)")
    @Column(nullable = false, length = 11)
    private String telefone2Pai;

    @NotBlank(message = "Nome do responsável é obrigatório")
    @Size(max = 80, message = "Nome do responsável deve ter no máximo 80 caracteres")
    @Pattern(regexp = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$", message = "Use apenas letras e espaços")
    @Column(nullable = false, length = 80)
    private String nomeResponsavel;

    @NotBlank(message = "Telefone do responsável é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD + número, apenas dígitos (10 a 11)")
    @Column(nullable = false, length = 11)
    private String telefoneResponsavel;

    @NotBlank(message = "CPF do responsável é obrigatório")
    @CPF(message = "CPF inválido")
    @Column(nullable = false, length = 11)
    private String cpfResponsavel;

    @NotBlank(message = "RG do responsável é obrigatório")
    @Pattern(regexp = "\\d{5,12}", message = "RG deve conter apenas dígitos (5 a 12)")
    @Column(nullable = false, length = 12)
    private String rgResponsavel;

    @NotBlank(message = "Local de trabalho do responsável é obrigatório")
    @Size(max = 120, message = "Local de trabalho do responsável deve ter no máximo 120 caracteres")
    @Column(nullable = false, length = 120)
    private String localTrabalhoResponsavel;

    @ManyToMany(mappedBy = "responsavel")
    private Set<FormularioCompleto> formularioCompleto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", nullable = false)
    @NotNull(message = "Endereço é obrigatório")
    @Valid
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getTelefoneMae() {
        return telefoneMae;
    }

    public void setTelefoneMae(String telefoneMae) {
        this.telefoneMae = telefoneMae;
    }

    public String getCpfMae() {
        return cpfMae;
    }

    public void setCpfMae(String cpfMae) {
        this.cpfMae = cpfMae;
    }

    public String getRgMae() {
        return rgMae;
    }

    public void setRgMae(String rgMae) {
        this.rgMae = rgMae;
    }

    public String getLocalTrabalhoMae() {
        return localTrabalhoMae;
    }

    public void setLocalTrabalhoMae(String localTrabalhoMae) {
        this.localTrabalhoMae = localTrabalhoMae;
    }

    public String getTelefone2Mae() {
        return telefone2Mae;
    }

    public void setTelefone2Mae(String telefone2Mae) {
        this.telefone2Mae = telefone2Mae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getTelefonePai() {
        return telefonePai;
    }

    public void setTelefonePai(String telefonePai) {
        this.telefonePai = telefonePai;
    }

    public String getCpfPai() {
        return cpfPai;
    }

    public void setCpfPai(String cpfPai) {
        this.cpfPai = cpfPai;
    }

    public String getRgPai() {
        return rgPai;
    }

    public void setRgPai(String rgPai) {
        this.rgPai = rgPai;
    }

    public String getLocalTrabalhoPai() {
        return localTrabalhoPai;
    }

    public void setLocalTrabalhoPai(String localTrabalhoPai) {
        this.localTrabalhoPai = localTrabalhoPai;
    }

    public String getTelefone2Pai() {
        return telefone2Pai;
    }

    public void setTelefone2Pai(String telefone2Pai) {
        this.telefone2Pai = telefone2Pai;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public String getRgResponsavel() {
        return rgResponsavel;
    }

    public void setRgResponsavel(String rgResponsavel) {
        this.rgResponsavel = rgResponsavel;
    }

    public String getLocalTrabalhoResponsavel() {
        return localTrabalhoResponsavel;
    }

    public void setLocalTrabalhoResponsavel(String localTrabalhoResponsavel) {
        this.localTrabalhoResponsavel = localTrabalhoResponsavel;
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