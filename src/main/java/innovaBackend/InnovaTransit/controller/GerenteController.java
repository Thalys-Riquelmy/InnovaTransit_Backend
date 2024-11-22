package innovaBackend.InnovaTransit.controller;

import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.model.Gerente;
import innovaBackend.InnovaTransit.service.EmpresaService;
import innovaBackend.InnovaTransit.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gerente")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;
    
    @Autowired 
    private EmpresaService empresaService;

    // Endpoint para criar um novo gerente
    @PostMapping("/salvar")
    public ResponseEntity<Gerente> salvarGerente(@RequestBody Gerente gerente) {
        Gerente novoGerente = gerenteService.save(gerente);
        return new ResponseEntity<>(novoGerente, HttpStatus.CREATED);
    }

    // Endpoint para obter todos os gerentes
    @GetMapping("/todos")
    public ResponseEntity<List<Gerente>> obterTodosGerentes() {
        List<Gerente> gerentes = gerenteService.findAll();
        return new ResponseEntity<>(gerentes, HttpStatus.OK);
    }

    // Endpoint para obter um gerente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Gerente> obterGerentePorId(@PathVariable Integer id) {
        Optional<Gerente> gerente = gerenteService.findById(id);
        return gerente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para atualizar um gerente existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Gerente> atualizarGerente(@PathVariable Integer id, @RequestBody Gerente gerenteDetalhes) {
        Optional<Gerente> gerenteExistente = gerenteService.findById(id);
        if (gerenteExistente.isPresent()) {
            Gerente gerenteAtualizado = gerenteExistente.get();
            gerenteAtualizado.setNome(gerenteDetalhes.getNome());
            gerenteAtualizado.setEmail(gerenteDetalhes.getEmail());
            gerenteAtualizado.setSenha(gerenteDetalhes.getSenha());
            gerenteAtualizado.setTrocarSenha(gerenteDetalhes.isTrocarSenha());
            gerenteService.save(gerenteAtualizado);
            return new ResponseEntity<>(gerenteAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para deletar um gerente por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarGerente(@PathVariable Integer id) {
        Optional<Gerente> gerente = gerenteService.findById(id);
        if (gerente.isPresent()) {
            gerenteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
