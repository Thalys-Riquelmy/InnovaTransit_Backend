package innovaBackend.InnovaTransit.integracao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.TesteResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;

@Service
public class IntegracaoService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:3000";

    public List<FolhaServicoResponse> getDataFolhaServico() {
        return getDataList(BASE_URL + "/folha-servico", new ParameterizedTypeReference<List<FolhaServicoResponse>>() {});
    }

    public List<MotoristaResponse> getDataMotorista() {
        return getDataList(BASE_URL + "/motorista", new ParameterizedTypeReference<List<MotoristaResponse>>() {});
    }

    public List<VeiculoResponse> getDataVeiculo() {
        return getDataList(BASE_URL + "/veiculo", new ParameterizedTypeReference<List<VeiculoResponse>>() {});
    }

    public TesteResponse getDataTesteResponse() {
        return getDataSingle(BASE_URL + "/teste", TesteResponse.class);
    }

    private <T> List<T> getDataList(String url, ParameterizedTypeReference<List<T>> responseType) {
        try {
            ResponseEntity<List<T>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Erro ao consumir API: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Erro do cliente/servidor ao consumir API: " + e.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao consumir API", e);
        }
    }

    private <T> T getDataSingle(String url, Class<T> responseType) {
        try {
            ResponseEntity<T> response = restTemplate.getForEntity(url, responseType);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Erro ao consumir API: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Erro do cliente/servidor ao consumir API: " + e.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao consumir API", e);
        }
    }
}
