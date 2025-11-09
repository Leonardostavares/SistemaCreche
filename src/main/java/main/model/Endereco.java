package main.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {
    
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @NotBlank(message = "Endereço é obrigatório")
    @Size(max = 120, message = "Endereço deve ter no máximo 120 caracteres")
    @Column(nullable = false, length = 120)
    private String endereco;
    
    @Size(max = 120, message = "Ponto de referência deve ter no máximo 120 caracteres")
    @Column(length = 120)
    private String pontoReferencia;
    
    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 60, message = "Bairro deve ter no máximo 60 caracteres")
    @Column(nullable = false, length = 60)
    private String bairro;
    
    @Size(max = 10, message = "Número deve ter no máximo 10 caracteres")
    @Column(length = 10)
    private String numero;
    
    @NotBlank(message = "Município é obrigatório")
    @Size(max = 60, message = "Município deve ter no máximo 60 caracteres")
    @Column(nullable = false, length = 60)   
    private String municipio;
    
    @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos")
    @Column(length = 8)
    private String cep;
    
    @NotBlank(message = "UF é obrigatória")
    @Pattern(regexp = "^[A-Z]{2}$", message = "UF deve conter 2 letras maiúsculas")
    @Column(nullable = false, length = 2)
    private String UF = "MA";

    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD + número, apenas dígitos (10 a 11)")
    @Column(length = 11)
    private String telefoneResidencial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String uF) {
        UF = uF;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    

}
