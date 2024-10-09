package innovaBackend.InnovaTransit.integracao;

import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.TesteResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IntegracaoServiceTest {

    @InjectMocks
    private IntegracaoService integracaoService;

    @MockBean // Muda para @MockBean para que o contexto do Spring o reconheça
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDataFolhaServico() {
        // Criação de um objeto simulado de FolhaServicoResponse
        MotoristaResponse motorista = new MotoristaResponse("Carlos", 1, "carlos@example.com");
        VeiculoResponse veiculo = new VeiculoResponse(1010, 50000, 100);
        FolhaServicoResponse folhaServicoResponse = new FolhaServicoResponse(
                "Observação",
                LocalDate.now(),
                LocalTime.of(8, 0),
                LocalTime.of(18, 0),
                LocalTime.of(8, 0),
                LocalTime.of(18, 0),
                motorista, veiculo, null);

        List<FolhaServicoResponse> expectedResponse = Arrays.asList(folhaServicoResponse);

        // Mock da chamada do RestTemplate
        when(restTemplate.exchange(
                "http://localhost:3000/folha-servico",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FolhaServicoResponse>>() {})
        ).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        // Chama o método e verifica a resposta
        List<FolhaServicoResponse> response = integracaoService.getDataFolhaServico();
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetDataMotorista() {
        // Criação de um objeto simulado de MotoristaResponse
        List<MotoristaResponse> expectedResponse = Arrays.asList(
                new MotoristaResponse("Carlos", 1, "carlos@example.com"),
                new MotoristaResponse("Maria", 2, "maria@example.com")
        );

        // Mock da chamada do RestTemplate
        when(restTemplate.exchange(
                "http://localhost:3000/motorista",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MotoristaResponse>>() {})
        ).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        // Chama o método e verifica a resposta
        List<MotoristaResponse> response = integracaoService.getDataMotorista();
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetDataVeiculo() {
        // Criação de um objeto simulado de VeiculoResponse
        List<VeiculoResponse> expectedResponse = Arrays.asList(
                new VeiculoResponse(1010, 50000, 100),
                new VeiculoResponse(1020, 60000, 200)
        );

        // Mock da chamada do RestTemplate
        when(restTemplate.exchange(
                "http://localhost:3000/veiculo",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VeiculoResponse>>() {})
        ).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        // Chama o método e verifica a resposta
        List<VeiculoResponse> response = integracaoService.getDataVeiculo();
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetDataTesteResponse() {
        // Criação de um objeto simulado de TesteResponse
        TesteResponse expectedResponse = new TesteResponse("Teste Funcionou");

        // Mock da chamada do RestTemplate
        when(restTemplate.getForEntity(
                "http://localhost:3000/teste",
                TesteResponse.class)
        ).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        // Chama o método e verifica a resposta
        TesteResponse response = integracaoService.getDataTesteResponse();
        assertEquals(expectedResponse, response);
    }
}
