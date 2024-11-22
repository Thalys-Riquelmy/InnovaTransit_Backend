package innovaBackend.InnovaTransit.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.FolhaServico;

@Repository
public interface FolhaServicoRepository extends JpaRepository<FolhaServico, Long> {
	List<FolhaServico> findByDataServico(LocalDate dataServico);
	FolhaServico findByDataServicoAndMotorista_Matricula(LocalDate dataServico, Integer matricula);
	
	List<FolhaServico> findByDataServicoAndEmpresaId(LocalDate dataServico, Long empresaId);
}
