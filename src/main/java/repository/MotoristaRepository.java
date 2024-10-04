package repository;

import model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
}
