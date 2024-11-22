package innovaBackend.InnovaTransit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
	Motorista findByMatricula(int matricula);
	 Optional<Motorista> findByEmail(String email);
	 
	 Motorista findByMatriculaAndEmpresaId(int matricula, Long empresaId);
}
