package innovaBackend.InnovaTransit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void deleteById(Long id) {
    	empresaRepository.deleteById(id);
    }
}
