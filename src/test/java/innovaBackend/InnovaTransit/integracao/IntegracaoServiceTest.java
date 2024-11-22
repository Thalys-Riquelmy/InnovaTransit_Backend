package innovaBackend.InnovaTransit.integracao;

import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class IntegracaoServiceTest {

    @Autowired
    private IntegracaoService integracaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testGetDataFolhaServico() {
//        List<FolhaServicoResponse> response = integracaoService.getDataFolhaServico();
//        assertNotNull(response);
//    }
//
//    @Test
//    public void testGetDataMotorista() {
//        List<MotoristaResponse> response = integracaoService.getDataMotorista();
//        assertNotNull(response);
//    }
//
//    @Test
//    public void testGetDataVeiculo() {
//        List<VeiculoResponse> response = integracaoService.getDataVeiculo();
//        assertNotNull(response);
//    }
}
