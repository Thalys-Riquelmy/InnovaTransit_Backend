package innovaBackend.InnovaTransit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import innovaBackend.InnovaTransit.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	Empresa findByCronExpression(String cronExpression);

}
