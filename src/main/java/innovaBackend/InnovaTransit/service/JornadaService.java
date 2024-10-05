package innovaBackend.InnovaTransit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.model.Jornada;
import innovaBackend.InnovaTransit.repository.JornadaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    public List<Jornada> findAll() {
        return jornadaRepository.findAll();
    }

    public Optional<Jornada> findById(Long id) {
        return jornadaRepository.findById(id);
    }

    public Jornada save(Jornada jornada) {
        return jornadaRepository.save(jornada);
    }

    public void deleteById(Long id) {
        jornadaRepository.deleteById(id);
    }
}
