package repository;
import org.springframework.data.jpa.repository.JpaRepository;
import model.FormularioCompleto;


public interface FormularioRepositorio extends JpaRepository<FormularioCompleto, Long> {
}


