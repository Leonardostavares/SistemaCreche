package model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
public class Endereco {
    
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private String pontoReferencia;
    
    @Column(nullable = false)
    private String bairro;
    
    @Column(nullable = false)
    private String numero;
    
    @Column(nullable = false)   
    private String municipio;
    
    @Column(nullable = false)
    private String cep;
    
    @Column(nullable = false)
    private String UF = "MA";

    @Column(nullable = false)
    private String telefoneResidencial;

}
