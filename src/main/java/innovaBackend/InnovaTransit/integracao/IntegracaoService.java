package innovaBackend.InnovaTransit.integracao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;
import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.repository.EmpresaRepository;

@Service
public class IntegracaoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmpresaRepository empresaRepository; 
    
    // Método para buscar URL da empresa
    public String getBaseUrl(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return empresa.getUrlBusca();  // Retorna a URL configurada para a empresa
    }

    public List<FolhaServicoResponse> getDataFolhaServico(Long empresaId) {
        // Obtém a URL da empresa
        String baseUrl = getBaseUrl(empresaId);
        
        // Pega a data atual do sistema
        LocalDate dataAtual = LocalDate.now();
        
//        // Define o formato desejado: "dd/MM/yyyy"
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        
//        // Converte a data atual para String no formato "dd/MM/yyyy"
//        String dataFormatada = dataAtual.format(formatter);
        
     // Define o formato desejado: "yyyy-MM-dd" (padrão ISO-8601)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Converte a data atual para String no formato "yyyy-MM-dd"
        String dataFormatada = dataAtual.format(formatter);

        return getDataList(baseUrl + "/folha-servico?data=" + dataFormatada, new ParameterizedTypeReference<List<FolhaServicoResponse>>() {});
    }

    public List<MotoristaResponse> getDataMotorista(Long empresaId) {
        // Obtém a URL da empresa
        String baseUrl = getBaseUrl(empresaId);
        return getDataList(baseUrl + "/motorista", new ParameterizedTypeReference<List<MotoristaResponse>>() {});
    }

    public List<VeiculoResponse> getDataVeiculo(Long empresaId) {
        // Obtém a URL da empresa
        String baseUrl = getBaseUrl(empresaId);
        return getDataList(baseUrl + "/veiculo", new ParameterizedTypeReference<List<VeiculoResponse>>() {});
    }
    
//    private <T> List<T> getDataList(String url, ParameterizedTypeReference<List<T>> responseType) {
//        try {
//            ResponseEntity<List<T>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
//            if (response.getStatusCode() == HttpStatus.OK) {
//                return response.getBody();
//            } else {
//                throw new RuntimeException("Erro ao consumir API: " + response.getStatusCode());
//            }
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            throw new RuntimeException("Erro do cliente/servidor ao consumir API: " + e.getStatusCode());
//        } catch (Exception e) {
//            throw new RuntimeException("Erro inesperado ao consumir API", e);
//        }
//    }
    
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
        } catch (ResourceAccessException e) {
            // Erro de conexão recusada ou problemas de rede
            throw new RuntimeException("Erro inesperado ao consumir API: Falha de conexão com o servidor.");
        } catch (Exception e) {
            // Captura geral para outros tipos de erro inesperado
            throw new RuntimeException("Erro inesperado ao consumir API.", e);
        }
    }

}
