package innovaBackend.InnovaTransit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.model.Gerente;
import innovaBackend.InnovaTransit.repository.EmpresaRepository;
import innovaBackend.InnovaTransit.repository.GerenteRepository;

@Service
public class GerenteService {

	@Autowired
    private GerenteRepository gerenteRepository;
	
	@Autowired
    private EmpresaRepository empresaRepository;

	// Método para buscar uma empresa pelo ID
    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id); // O findById espera Long
    }

    public List<Gerente> findAll() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> findById(Integer id) {
        return gerenteRepository.findById(id);
    }

    public Gerente save(Gerente gerente) {
    	gerente.setTrocarSenha(true);
    	gerente.setSenha(null);
        return gerenteRepository.save(gerente);
    }

    public void deleteById(Integer id) {
    	gerenteRepository.deleteById(id);
    }
}
