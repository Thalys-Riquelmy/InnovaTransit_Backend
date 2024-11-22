package innovaBackend.InnovaTransit.automacaoAPIExterna;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import innovaBackend.InnovaTransit.jobs.JobAutomatization;
import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.repository.EmpresaRepository;

@RestController
@RequestMapping("/api/automacao")
public class AutomacaoController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AutomacaoService automacaoService;
    
    @Autowired
    private JobAutomatization jobAutomatization;

    // Endpoint para configurar a automação de uma empresa
    @PostMapping("/configurar")
    public ResponseEntity<Map<String, String>> configurarAutomacao(@RequestParam Long idEmpresa,
                                       @RequestParam String tipoAutomacao,
                                       @RequestParam String hora,
                                       @RequestParam String minuto,
                                       @RequestParam(required = false) String diaSemana,
                                       @RequestParam(required = false) String diaMes) {

        // Construir a expressão Cron com base nos parâmetros fornecidos pelo usuário
        String cronExpression = buildCronExpression(tipoAutomacao, hora, minuto, diaSemana, diaMes);

        // Encontrar a empresa no banco de dados e atualizar a expressão Cron
        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        
        // Atualizando a expressão Cron da empresa no banco de dados
        empresa.setCronExpression(cronExpression);
        empresaRepository.save(empresa);

        // Agendar a automação com a expressão Cron configurada
        automacaoService.agendarAutomacao(idEmpresa);
     
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Automação configurada com sucesso para a empresa: " + empresa.getNome());

        // Retornar o status de sucesso com a resposta JSON
        return ResponseEntity.ok().body(response);
    }

    // Método para construir a expressão Cron com base no tipo de automação
    private String buildCronExpression(String tipoAutomacao, String hora, String minuto, String diaSemana, String diaMes) {
        switch (tipoAutomacao) {
            case "diario":
                return String.format("0 %s %s * * ?", minuto, hora);
            case "semanal":
                return String.format("0 %s %s * * %s", minuto, hora, diaSemana);
            case "mensal":
                return String.format("0 %s %s %s * ?", minuto, hora, diaMes);
            default:
                throw new IllegalArgumentException("Tipo de automação inválido.");
        }
    }
    
    // Novo método para atualizar os dados de uma empresa
//    @PostMapping("/atualizar-dados/{idEmpresa}")
//    public ResponseEntity<Map<String, String>> atualizarDados(@PathVariable Long idEmpresa) {
//
//        Map<String, String> response = new HashMap<>();
//        
//        try {
//            // Chama o método atualizarDados do JobAutomatization passando o id da empresa
//            jobAutomatization.atualizarDados(idEmpresa);
//
//            // Prepara a resposta de sucesso
//            response.put("status", "success");
//            response.put("message", "Dados da empresa com ID " + idEmpresa + " atualizados com sucesso.");
//        } catch (Exception e) {
//            // Caso ocorra algum erro, retorna um erro
//            response.put("status", "error");
//            response.put("message", "Erro ao atualizar dados para a empresa com ID " + idEmpresa + ": " + e.getMessage());
//        }
//
//        // Retorna a resposta JSON
//        return ResponseEntity.ok().body(response);
//    }
    
    @PostMapping("/atualizar-dados/{idEmpresa}")
    public ResponseEntity<Map<String, String>> atualizarDados(@PathVariable Long idEmpresa) {
        Map<String, String> response = new HashMap<>();
        
        try {
            // Chama o método atualizarDados do JobAutomatization passando o id da empresa
            jobAutomatization.atualizarDados(idEmpresa);

            // Se tudo correr bem, prepara a resposta de sucesso
            response.put("status", "success");
            response.put("message", "Dados da empresa com ID " + idEmpresa + " atualizados com sucesso.");
        } catch (RuntimeException e) {
            // Caso ocorra algum erro, captura a exceção e envia uma mensagem clara para o cliente
            response.put("status", "error");
            response.put("message", e.getMessage()); // A mensagem amigável será retornada
        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada
            response.put("status", "error");
            response.put("message", "Erro inesperado ao processar a solicitação.");
        }

        // Retorna a resposta JSON para o cliente
        return ResponseEntity.ok().body(response);
    }

}
