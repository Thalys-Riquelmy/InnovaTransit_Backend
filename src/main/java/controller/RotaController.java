package controller;

import model.Rota;
import service.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rota")
@CrossOrigin(origins = "http://localhost:4200")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @GetMapping
    public ResponseEntity<List<Rota>> listarRotas() {
        List<Rota> rotas = rotaService.findAll();
        return ResponseEntity.ok(rotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rota> obterRotaPorId(@PathVariable Long id) {
        return rotaService.findById(id)
                .map(rota -> ResponseEntity.ok(rota))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rota> criarRota(@RequestBody Rota rota) {
        Rota novaRota = rotaService.save(rota);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rota> atualizarRota(@PathVariable Long id, @RequestBody Rota rotaAtualizada) {
        if (!rotaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        rotaAtualizada.setId(id);
        Rota rotaSalva = rotaService.save(rotaAtualizada);
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
