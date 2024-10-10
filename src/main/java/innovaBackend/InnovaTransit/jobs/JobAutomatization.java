package innovaBackend.InnovaTransit.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import innovaBackend.InnovaTransit.integracao.IntegracaoService;
import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;


@Component
@EnableScheduling
public class JobAutomatization {
	
	@Autowired
    private IntegracaoService integracaoService;
	
//	@Scheduled(cron = "0 0 0 * * ?")
    public void atualizarDados() {
        try {        	
            List<FolhaServicoResponse> folhasServico = integracaoService.getDataFolhaServico();
            List<MotoristaResponse> motoristas = integracaoService.getDataMotorista();
            List<VeiculoResponse> veiculos = integracaoService.getDataVeiculo();

            System.out.println("Folhas de serviço: " + folhasServico);
            System.out.println("Motoristas: " + motoristas);
            System.out.println("Veículos: " + veiculos);
        } catch (Exception e) {
            e.printStackTrace(); // Mostra a stack trace do erro
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
        }
    }
}

//1: Segundo (preenchido de 0 a 59)
//2: Minuto (preenchido de 0 a 59)
//3: Hora (preenchido de 0 a 23)
//4: Dia (preenchido de 0 a 31)
//5: Mês (preenchido de 1 a 12)
//6: Dia da semana (preenchido de 0 a 6)
