package innovaBackend.InnovaTransit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import innovaBackend.InnovaTransit.model.Veiculo;
import innovaBackend.InnovaTransit.service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/api/veiculo")
@CrossOrigin(origins = "http://localhost:4200")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.findAll();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> obterVeiculoPorId(@PathVariable Long id) {
        return veiculoService.findById(id)
                .map(veiculo -> ResponseEntity.ok(veiculo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        if (!veiculoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        veiculoAtualizado.setId(id);
        Veiculo veiculoSalvo = veiculoService.save(veiculoAtualizado);
        return ResponseEntity.ok(veiculoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        if (!veiculoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        veiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
