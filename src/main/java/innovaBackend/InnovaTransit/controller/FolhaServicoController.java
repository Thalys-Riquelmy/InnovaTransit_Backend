package innovaBackend.InnovaTransit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.responseDTO.FolhaServicoDTO;
import innovaBackend.InnovaTransit.service.FolhaServicoService;

import java.awt.PageAttributes.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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

    
   // Endpoint para iniciar a folha de serviço
    @PostMapping(value = "/iniciar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> iniciarFolhaDeServico(@RequestBody Map<String, Object> payload) {
        Long id = ((Number) payload.get("id")).longValue();
        String horaInicialString = (String) payload.get("horaInicial");
        LocalTime horaInicial = LocalTime.parse(horaInicialString);

        try {
            folhaServicoService.iniciarFolhaDeServico(id, horaInicial);
            return ResponseEntity.ok().body(Map.of("message", "Hora inicial salva com sucesso as: " + horaInicial));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
   
    //Metodo para iniciar tarefa
    @PostMapping(value= "/iniciar-tarefa", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> iniciarTarefa(@RequestBody Map<String, Object> payload){
    	Long id = ((Number) payload.get("id")).longValue();
    	String horaInicioString = (String) payload.get("horaInicio");
    	LocalTime horaInicio = LocalTime.parse(horaInicioString);
    	
    	try {
    		folhaServicoService.iniciarTarefa(id, horaInicio);
    		return ResponseEntity.ok().body(Map.of("message", "Tarefa iniciada com sucesso as: " + horaInicio));
    	} catch (RuntimeException e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    	}
    }
    
    @PostMapping(value = "/finalizar-tarefa", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> finalizarTarefa(@RequestBody Map<String, Object> payload) {
        Long id = ((Number) payload.get("id")).longValue();
        String horaFimString = (String) payload.get("horaFim");
        LocalTime horaFim = LocalTime.parse(horaFimString);
        Integer hodometroFinal = Integer.parseInt(payload.get("hodometroFinal").toString());
        Integer catracaFinal = Integer.parseInt(payload.get("catracaFinal").toString());

        try {
            folhaServicoService.finalizarTarefa(id, horaFim, hodometroFinal, catracaFinal);
            return ResponseEntity.ok().body(Map.of("message", "Tarefa finalizada com sucesso às: " + horaFim));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping(value = "/cancelar-tarefa", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> cancelarTarefa(@RequestBody Map<String, Object> payload){
    	Long id = ((Number) payload.get("id")).longValue();
    	String motivoCancelamento = (String) payload.get("motivoCancelamento");
    	String horaFimString = (String) payload.get("horaFim");
    	LocalTime horaFim = LocalTime.parse(horaFimString); 
    	
    	try {
    		folhaServicoService.cancelarTarefa(id, horaFim, motivoCancelamento);
    		return ResponseEntity.ok().body(Map.of("message", "Tarefa cancelada com sucesso às: " + horaFim));
    	} catch (RuntimeException e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    	}
    }
    
    @PostMapping(value = "/finalizar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> finalizarFolhaDeServico(@RequestBody Map<String, Object> payload){
    	Long id = ((Number) payload.get("id")).longValue();
    	String horaFimString = (String) payload.get("horaFim");
    	LocalTime horaFim = LocalTime.parse(horaFimString); 
    	
    	try {
    		folhaServicoService.finalizarFolhaServico(id, horaFim);
    		return ResponseEntity.ok().body(Map.of("message", "Folha de Serviço finalizada com sucesso às: " + horaFim));
    	} catch (RuntimeException e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    	}
    }

}
