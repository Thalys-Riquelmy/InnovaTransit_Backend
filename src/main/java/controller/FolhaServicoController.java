package controller;

import model.FolhaServico;
import service.FolhaServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folha-servico")
@CrossOrigin(origins = "http://localhost:4200")
public class FolhaServicoController {

    @Autowired
    private FolhaServicoService folhaServicoService;

    @GetMapping
    public ResponseEntity<List<FolhaServico>> listarFolhaServico() {
        List<FolhaServico> folhas = folhaServicoService.findAll();
        return ResponseEntity.ok(folhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolhaServico> obterFolhaServicoPorId(@PathVariable Long id) {
        return folhaServicoService.findById(id)
                .map(folha -> ResponseEntity.ok(folha))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FolhaServico> criarFolhaServico(@RequestBody FolhaServico folhaServico) {
        FolhaServico novaFolha = folhaServicoService.save(folhaServico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFolha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FolhaServico> atualizarFolhaServico(@PathVariable Long id, @RequestBody FolhaServico folhaServicoAtualizada) {
        if (!folhaServicoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        folhaServicoAtualizada.setId(id);
        FolhaServico folhaAtualizada = folhaServicoService.save(folhaServicoAtualizada);
        return ResponseEntity.ok(folhaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFolhaServico(@PathVariable Long id) {
        if (!folhaServicoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        folhaServicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
