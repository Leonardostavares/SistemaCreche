package main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import main.model.FormularioCompleto;


public interface FormularioRepositorio extends JpaRepository<FormularioCompleto, Long> {
}


