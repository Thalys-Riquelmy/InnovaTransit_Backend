package innovaBackend.InnovaTransit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import innovaBackend.InnovaTransit.model.Motorista;
import innovaBackend.InnovaTransit.responseDTO.MotoristaDTO;
import innovaBackend.InnovaTransit.service.MotoristaService;

@RestController
@RequestMapping("/api/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @GetMapping
    public ResponseEntity<List<Motorista>> listarMotoristas() {
        List<Motorista> motoristas = motoristaService.findAll();
        return ResponseEntity.ok(motoristas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorista> obterMotoristaPorId(@PathVariable int id) {
        return motoristaService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Motorista> criarMotorista(@RequestBody Motorista motorista) {
        Motorista novoMotorista = motoristaService.save(motorista);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMotorista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motorista> atualizarMotorista(@PathVariable int id, @RequestBody Motorista motoristaAtualizado) {
        return motoristaService.findById(id)
            .map(motoristaExistente -> {
                motoristaAtualizado.setId(id);
                Motorista motoristaSalvo = motoristaService.save(motoristaAtualizado);
                return ResponseEntity.ok(motoristaSalvo);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMotorista(@PathVariable int id) {
        if (motoristaService.findById(id).isPresent()) {
            motoristaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/por-email")
    public ResponseEntity<MotoristaDTO> buscarPorEmail(@RequestParam String email) {
        Optional<Motorista> motoristaOptional = motoristaService.buscarMotorista(email);
        
        if (motoristaOptional.isPresent()) {
            // Converter motorista para MotoristaDTO
            MotoristaDTO motoristaDTO = new MotoristaDTO();
            Motorista motorista = motoristaOptional.get();
            motoristaDTO.setId(motorista.getId());
            motoristaDTO.setNome(motorista.getNome());
            motoristaDTO.setEmail(motorista.getEmail());
            motoristaDTO.setMatricula(motorista.getMatricula());
            motoristaDTO.setEmpresa(motorista.getEmpresa());
            return ResponseEntity.ok(motoristaDTO);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }
    }
}
