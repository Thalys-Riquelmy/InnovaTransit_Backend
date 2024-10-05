package innovaBackend.InnovaTransit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.Rota;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Long> {
	
}
