package innovaBackend.InnovaTransit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.Jornada;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long> {
	
}
