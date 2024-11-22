package innovaBackend.InnovaTransit.automacaoAPIExterna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.integracao.IntegracaoService;
import innovaBackend.InnovaTransit.jobs.JobAutomatization;
import innovaBackend.InnovaTransit.repository.EmpresaRepository;
import jakarta.annotation.PostConstruct;

@Service
public class AutomacaoService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private IntegracaoService integracaoService;
    
    @Autowired
    private JobAutomatization jobAutomatization;

    // Este método é responsável por realizar a automação (chamada à API externa)
    public void realizarBuscaAutomatica(Long idEmpresa) {
        // Recuperar a URL da API da empresa
        String urlBase = integracaoService.getBaseUrl(idEmpresa);

        // Chama os métodos de integração para importar dados da API
        try {
        	
        	// Chama o JobAutomatization para atualizar os dados
            jobAutomatization.atualizarDados(idEmpresa);  // Chama a integração do JobAutomatization
            
            System.out.println("Dados importados com sucesso para a empresa: " + idEmpresa);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao realizar integração para a empresa " + idEmpresa + ": " + e.getMessage());
        }
    }

    // Método para agendar a automação para uma empresa com base na expressão Cron armazenada no banco
    public void agendarAutomacao(Long idEmpresa) {
        // Recuperando a expressão Cron armazenada no banco para a empresa específica
        String cronExpression = empresaRepository.findById(idEmpresa)
                .map(empresa -> empresa.getCronExpression())  // Pegando a expressão Cron da empresa
                .orElseThrow(() -> new RuntimeException("Expressão Cron não encontrada para a empresa"));

        // Usando o `ScheduledTaskRegistrar` para registrar a tarefa com a expressão Cron dinâmica
        ScheduledTaskRegistrar taskRegistrar = new ScheduledTaskRegistrar();
        taskRegistrar.addCronTask(() -> realizarBuscaAutomatica(idEmpresa), cronExpression);
        taskRegistrar.afterPropertiesSet();  // Isso configura o agendamento da tarefa

        System.out.println("Tarefa agendada para a empresa com a expressão Cron: " + cronExpression);
    }
    
    // Método que será executado após a inicialização do Spring
    @PostConstruct
    public void agendarAutomacoesParaTodasEmpresas() {
        // Carregar todas as empresas e agendar a automação para cada uma delas
        empresaRepository.findAll().forEach(empresa -> {
            if (empresa.getCronExpression() != null && !empresa.getCronExpression().isEmpty()) {
                // Agendar a automação para cada empresa com a expressão Cron configurada
                agendarAutomacao(empresa.getId());
            } else {
                System.out.println("Empresa " + empresa.getId() + " não tem expressão Cron configurada.");
            }
        });
    }
}
