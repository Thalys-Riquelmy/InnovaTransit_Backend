package innovaBackend.InnovaTransit.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import innovaBackend.InnovaTransit.integracao.IntegracaoService;
import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;
import innovaBackend.InnovaTransit.service.ImportacaoService;

@Component
public class JobAutomatization {

    @Autowired
    private IntegracaoService integracaoService;

    @Autowired
    private ImportacaoService importacaoService;

    // Atualiza e importa os dados
//    public void atualizarDados(Long empresaId) {
//        try {
//            System.out.println("Atualizando dados para a empresa com ID: " + empresaId);
//
//            // Obter os dados da API externa (motoristas, veículos, folhas de serviço)
//            List<FolhaServicoResponse> folhasServico = integracaoService.getDataFolhaServico(empresaId);
//            List<MotoristaResponse> motoristas = integracaoService.getDataMotorista(empresaId);
//            List<VeiculoResponse> veiculos = integracaoService.getDataVeiculo(empresaId);
//
//            // Verifique se os dados foram obtidos corretamente antes de prosseguir
//            if (folhasServico != null && motoristas != null && veiculos != null) {
//                System.out.println("Dados obtidos com sucesso:");
//
//                System.out.println("Folhas de serviço: " + folhasServico);
//                System.out.println("Motoristas: " + motoristas);
//                System.out.println("Veículos: " + veiculos);
//
//                // Chame o método de importação para salvar os dados no banco
//                importacaoService.importar(empresaId);
//                System.out.println("Dados importados para o banco de dados com sucesso.");
//            } else {
//                System.out.println("Erro: Dados não obtidos corretamente da API.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Erro ao atualizar dados: " + e.getMessage());
//        }
//    }
    
    public void atualizarDados(Long empresaId) {
        try {
            System.out.println("Atualizando dados para a empresa com ID: " + empresaId);

            // Obter os dados da API externa (motoristas, veículos, folhas de serviço)
            List<FolhaServicoResponse> folhasServico = integracaoService.getDataFolhaServico(empresaId);
            List<MotoristaResponse> motoristas = integracaoService.getDataMotorista(empresaId);
            List<VeiculoResponse> veiculos = integracaoService.getDataVeiculo(empresaId);

            // Verifique se os dados foram obtidos corretamente antes de prosseguir
            if (folhasServico != null && motoristas != null && veiculos != null) {
                System.out.println("Dados obtidos com sucesso:");

                System.out.println("Folhas de serviço: " + folhasServico);
                System.out.println("Motoristas: " + motoristas);
                System.out.println("Veículos: " + veiculos);

                // Chame o método de importação para salvar os dados no banco
                importacaoService.importar(empresaId);
                System.out.println("Dados importados para o banco de dados com sucesso.");
            } else {
                System.out.println("Erro: Dados não obtidos corretamente da API.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
            throw e;  // Re-lança a exceção para que ela possa ser capturada no controlador
        }
    }

}

