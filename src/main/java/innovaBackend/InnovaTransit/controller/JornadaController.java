package innovaBackend.InnovaTransit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import innovaBackend.InnovaTransit.model.Jornada;
import innovaBackend.InnovaTransit.service.JornadaService;

import java.util.List;

@RestController
@RequestMapping("/api/jornada")
@CrossOrigin(origins = "http://localhost:4200")
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    @GetMapping
    public ResponseEntity<List<Jornada>> listarJornadas() {
        List<Jornada> jornadas = jornadaService.findAll();
        return ResponseEntity.ok(jornadas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jornada> obterJornadaPorId(@PathVariable Long id) {
        return jornadaService.findById(id)
                .map(jornada -> ResponseEntity.ok(jornada))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jornada> criarJornada(@RequestBody Jornada jornada) {
        Jornada novaJornada = jornadaService.save(jornada);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaJornada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jornada> atualizarJornada(@PathVariable Long id, @RequestBody Jornada jornadaAtualizada) {
        if (!jornadaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        jornadaAtualizada.setId(id);
        Jornada jornadaSalva = jornadaService.save(jornadaAtualizada);
        return ResponseEntity.ok(jornadaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJornada(@PathVariable Long id) {
        if (!jornadaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        jornadaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
