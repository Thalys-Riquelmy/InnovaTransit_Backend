package innovaBackend.InnovaTransit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Veiculo findByNumeroVeiculo(int numeroVeiculo);
    
    Veiculo findByNumeroVeiculoAndEmpresaId(int numeroVeiculo, Long empresaId);
}
