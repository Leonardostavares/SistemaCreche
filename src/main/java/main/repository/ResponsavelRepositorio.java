package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.model.Responsavel;
import java.util.Optional;

@Repository
public interface ResponsavelRepositorio extends JpaRepository<Responsavel, Long> {
    
    /**
     * Busca um responsável pelo CPF (para reutilização)
     */
    Optional<Responsavel> findByCpf(String cpf);
    
    /**
     * Verifica se existe um responsável com determinado CPF
     */
    boolean existsByCpf(String cpf);
}