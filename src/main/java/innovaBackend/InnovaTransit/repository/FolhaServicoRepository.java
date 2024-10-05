package innovaBackend.InnovaTransit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.FolhaServico;

@Repository
public interface FolhaServicoRepository extends JpaRepository<FolhaServico, Long> {
	
}
