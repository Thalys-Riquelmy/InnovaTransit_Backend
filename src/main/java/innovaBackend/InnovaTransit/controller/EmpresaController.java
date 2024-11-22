package innovaBackend.InnovaTransit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.service.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

	@Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresas = empresaService.findAll();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterVeiculoPorId(@PathVariable Long id) {
        return empresaService.findById(id)
                .map(veiculo -> ResponseEntity.ok(veiculo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa= empresaService.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        if (!empresaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        empresaAtualizada.setId(id);
        Empresa empresaSalva = empresaService.save(empresaAtualizada);
        return ResponseEntity.ok(empresaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        if (!empresaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        empresaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
