package repository;

import model.FolhaServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolhaServicoRepository extends JpaRepository<FolhaServico, Long> {
	
}
