package innovaBackend.InnovaTransit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import innovaBackend.InnovaTransit.model.Tarefa;
import innovaBackend.InnovaTransit.service.RotaService;

import java.util.List;

@RestController
@RequestMapping("/api/rota")
@CrossOrigin(origins = "http://localhost:4200")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarRotas() {
        List<Tarefa> rotas = rotaService.findAll();
        return ResponseEntity.ok(rotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterRotaPorId(@PathVariable Long id) {
        return rotaService.findById(id)
                .map(rota -> ResponseEntity.ok(rota))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarRota(@RequestBody Tarefa rota) {
        Tarefa novaRota = rotaService.save(rota);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarRota(@PathVariable Long id, @RequestBody Tarefa rotaAtualizada) {
        if (!rotaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        rotaAtualizada.setId(id);
        Tarefa rotaSalva = rotaService.save(rotaAtualizada);
        return ResponseEntity.ok(rotaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRota(@PathVariable Long id) {
        if (!rotaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        rotaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
