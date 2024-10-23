package innovaBackend.InnovaTransit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.model.Motorista;
import innovaBackend.InnovaTransit.repository.MotoristaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    public List<Motorista> findAll() {
        return motoristaRepository.findAll();
    }

    public Optional<Motorista> findById(int id) {
        return motoristaRepository.findById(id);
    }

    public Motorista save(Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    public void deleteById(int id) {
        motoristaRepository.deleteById(id);
    }
    
    public Optional<Motorista> buscarMotorista(String email) {
        return motoristaRepository.findByEmail(email);
    }
}
