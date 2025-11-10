package main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import main.model.FormularioCompleto;
import java.util.List;

@Repository
public interface FormularioRepositorio extends JpaRepository<FormularioCompleto, Long> {

    @Query("SELECT DISTINCT f FROM FormularioCompleto f " +
           "LEFT JOIN FETCH f.responsavel r " +
           "LEFT JOIN FETCH r.endereco " +
           "LEFT JOIN FETCH f.composicaoFamiliar " +
           "LEFT JOIN FETCH f.pessoasAutorizadas " +
           "WHERE r.cpf = :cpf")
    List<FormularioCompleto> findByCpfResponsavelComTodasInformacoes(@Param("cpf") String cpf);
    
    @Query("SELECT DISTINCT f FROM FormularioCompleto f " +
           "LEFT JOIN FETCH f.responsavel r " +
           "LEFT JOIN FETCH r.endereco " +
           "LEFT JOIN FETCH f.composicaoFamiliar " +
           "LEFT JOIN FETCH f.pessoasAutorizadas")
    List<FormularioCompleto> findAllComTodasInformacoes();
    
    @Query("SELECT DISTINCT f FROM FormularioCompleto f " +
           "LEFT JOIN FETCH f.responsavel r " +
           "LEFT JOIN FETCH r.endereco " +
           "LEFT JOIN FETCH f.composicaoFamiliar " +
           "LEFT JOIN FETCH f.pessoasAutorizadas " +
           "WHERE f.status = :status")
    List<FormularioCompleto> findByStatus(@Param("status") main.enums.Status status);
}


