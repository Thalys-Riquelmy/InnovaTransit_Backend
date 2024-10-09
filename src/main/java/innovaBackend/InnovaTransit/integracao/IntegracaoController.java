package innovaBackend.InnovaTransit.integracao;

import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.TesteResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Define um prefixo comum para todos os endpoints
public class IntegracaoController {

    @Autowired
    private IntegracaoService integracaoService;

    @GetMapping("/folha-servico")
    public List<FolhaServicoResponse> getFolhaServico() {
        return integracaoService.getDataFolhaServico();
    }

    @GetMapping("/motorista")
    public List<MotoristaResponse> getMotorista() {
        return integracaoService.getDataMotorista();
    }

    @GetMapping("/veiculo")
    public List<VeiculoResponse> getVeiculo() {
        return integracaoService.getDataVeiculo();
    }

    @GetMapping("/teste")
    public TesteResponse getTesteResponse() {
        return integracaoService.getDataTesteResponse();
    }
}
