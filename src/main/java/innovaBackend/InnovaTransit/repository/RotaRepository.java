package innovaBackend.InnovaTransit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.Tarefa;

@Repository
public interface RotaRepository extends JpaRepository<Tarefa, Long> {
	
}
