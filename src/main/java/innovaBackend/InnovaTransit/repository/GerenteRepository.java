package innovaBackend.InnovaTransit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import innovaBackend.InnovaTransit.model.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository <Gerente, Integer> {

}
