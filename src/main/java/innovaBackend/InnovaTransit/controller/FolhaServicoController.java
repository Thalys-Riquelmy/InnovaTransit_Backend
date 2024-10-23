package innovaBackend.InnovaTransit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.responseDTO.FolhaServicoDTO;
import innovaBackend.InnovaTransit.service.FolhaServicoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/folha-servico")
@CrossOrigin(origins = "http://localhost:8101")
public class FolhaServicoController {

    @Autowired
    private FolhaServicoService folhaServicoService;
    

    //Metodo para listar todas folhas de serviços
    @GetMapping
    public ResponseEntity<List<FolhaServico>> listarFolhaServico() {
        List<FolhaServico> folhas = folhaServicoService.findAll();
        return ResponseEntity.ok(folhas);
    }

    //Metodo para buscar folha de serviço pelo id 
    @GetMapping("/{id}")
    public ResponseEntity<FolhaServico> obterFolhaServicoPorId(@PathVariable Long id) {
        return folhaServicoService.findById(id)
                .map(folha -> ResponseEntity.ok(folha))
                .orElse(ResponseEntity.notFound().build());
    }

    //Metodo para criar folha de serviço
    @PostMapping
    public ResponseEntity<FolhaServico> criarFolhaServico(@RequestBody FolhaServico folhaServico) {
        FolhaServico novaFolha = folhaServicoService.save(folhaServico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFolha);
    }

    //Metodo para atualizar folha de serviço
    @PutMapping("/{id}")
    public ResponseEntity<FolhaServico> atualizarFolhaServico(@PathVariable Long id, @RequestBody FolhaServico folhaServicoAtualizada) {
        if (!folhaServicoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        folhaServicoAtualizada.setId(id);
        FolhaServico folhaAtualizada = folhaServicoService.save(folhaServicoAtualizada);
        return ResponseEntity.ok(folhaAtualizada);
    }
    
    //Metodo para deletar folha
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFolhaServico(@PathVariable Long id) {
        if (!folhaServicoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        folhaServicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    //Metodo para obter folha de serviço pela matricula
    @GetMapping("/obter/{matricula}")
    public ResponseEntity<FolhaServico> obterFolhaServicoPorMatricula(@PathVariable Integer matricula) {
        FolhaServico folhaServico = folhaServicoService.obterFolhaServicoDoMotorista(matricula);
        
        if (folhaServico != null) {
            return ResponseEntity.ok(folhaServico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 // Método para obter folha de serviço pela matrícula e data
    @GetMapping("/obter-por-matricula-e-data")
    public ResponseEntity<FolhaServicoDTO> obterFolhaServicoPorMatriculaEData(
            @RequestParam LocalDate dataServico,
            @RequestParam Integer matricula) {

        FolhaServicoDTO folhaServicoDTO = folhaServicoService.obterFolhaServicoPorDataEMatricula(dataServico, matricula);
        
        if (folhaServicoDTO != null) {
            return ResponseEntity.ok(folhaServicoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
//    //Metodo para iniciar folha de serviço
//    @PostMapping("/iniciar/{id}")
//    public ResponseEntity<String> iniciarFolhaDeServico(@PathVariable Long id) {
//        try {
//            folhaServicoService.iniciarFolhaDeServico(id);
//            return ResponseEntity.ok("Folha de serviço iniciada com sucesso.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(404).body(e.getMessage());
//        }
//    }
//    
//    //Metodo para iniciar tarefa
//    @PostMapping("/iniciar-tarefa/{id}")
//    public ResponseEntity<String> iniciarTarefa(@PathVariable Long id) {
//        try {
//        	folhaServicoService.iniciarTarefa(id);
//            return ResponseEntity.ok("Tarefa iniciada com sucesso.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(404).body(e.getMessage());
//        }
//    }

}
