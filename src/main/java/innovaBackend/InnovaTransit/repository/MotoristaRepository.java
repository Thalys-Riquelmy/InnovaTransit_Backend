package innovaBackend.InnovaTransit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
	Motorista findByMatricula(int matricula);
}
