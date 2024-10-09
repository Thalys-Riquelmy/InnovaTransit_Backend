package innovaBackend.InnovaTransit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.model.Tarefa;
import innovaBackend.InnovaTransit.repository.RotaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RotaService {

    @Autowired
    private RotaRepository rotaRepository;

    public List<Tarefa> findAll() {
        return rotaRepository.findAll();
    }

    public Optional<Tarefa> findById(Long id) {
        return rotaRepository.findById(id);
    }

    public Tarefa save(Tarefa rota) {
        return rotaRepository.save(rota);
    }

    public void deleteById(Long id) {
        rotaRepository.deleteById(id);
    }
}
